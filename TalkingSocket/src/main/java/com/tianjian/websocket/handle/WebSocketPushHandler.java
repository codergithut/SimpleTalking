package com.tianjian.websocket.handle;

import com.alibaba.fastjson.JSONObject;
import com.common.util.StringSortUtil;
import com.tianjian.redis.service.UserTopicInfo;
import com.common.domain.model.TalkingContent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;


/**
 * websocket 文字消息处理
 * Created by tianjian on 2019/3/4.
 */
@Service
public class WebSocketPushHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Map<String, WebSocketSession> webSocketSessionMap = new HashMap();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private UserTopicInfo userTopicInfo;


    /**
     * 用户进入系统监听
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("用户信息:" + session.getAttributes());
        Map<String, Object> map = session.getAttributes();
        webSocketSessionMap.put(map.get("userId").toString(), session);
    }

    /**
     * 处理用户请求
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String content = message.getPayload();
        if(StringUtils.isBlank(content)) {
            return ;
        }
        logger.info("receive message from client ={}", content);
        TalkingContent talkingContent = JSONObject.parseObject(content, TalkingContent.class);
        logger.info("talking content change " + JSONObject.toJSONString(talkingContent));
        sendMessageToUser(talkingContent, "websocket");

    }

    /**
     * 用户退出后的处理
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        webSocketSessionMap.remove(session.getAttributes().get("userId"));
        logger.info("xxx用户退出系统。。。");
    }

    /**
     * 自定义函数
     * 发送消息给指定的在线用户
     */
    public void sendMessageToUser(TalkingContent talkingContent, String type) {
        talkingContent.setConsume(false);
        talkingContent.setId(UUID.randomUUID().toString());
        String userId = talkingContent.getToId();
        logger.info("=========================== userId:" + userId + "==============================");

        /**
         * 如果是转发并发现服务socket已关闭,日志保存
         */
        if("kafka".equals(type) && !webSocketSessionMap.containsKey(userId)) {
            saveTalkingContentingLog(talkingContent);
            return ;
        }

        /**
         * 首次发送查看当前机器是否有发送用户
         */
        if("websocket".equals(type) && !webSocketSessionMap.containsKey(userId)) {
            String topic =  userTopicInfo.getTopicByUserId(talkingContent.getToId());
            if(!StringUtils.isBlank(topic)) {
                kafkaTemplate.send(topic, JSONObject.toJSONString(talkingContent));
                return ;
            } else {
                saveTalkingContentingLog(talkingContent);
                return ;
            }

        }

        /**
         * 当前节点含有目标用户发送 强校验,更加清晰易懂冗余代码if
         */
        if(webSocketSessionMap.containsKey(userId)) {
            try {
                WebSocketSession user = webSocketSessionMap.get(userId);
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(new TextMessage(JSONObject.toJSONString(talkingContent)));
                    talkingContent.setConsume(true);
                    saveTalkingContentingLog(talkingContent);
                    return ;
                }
            } catch (IOException e) {
                e.printStackTrace();
                saveTalkingContentingLog(talkingContent);
                return ;
            }
        }


    }

    /**
     * 保存日志到数据库
     * @param talkingContent 聊天日志
     */
    private void saveTalkingContentingLog(TalkingContent talkingContent) {
        String sign = StringSortUtil.getKeyByKeys(new String[]{talkingContent.getToId(), talkingContent.getFromId()});
        talkingContent.setSign(sign);
        kafkaTemplate.send("talkingContentLog", JSONObject.toJSONString(talkingContent));
        return ;
    }

    public boolean removeSocketByUserId(String userId) {

        WebSocketSession user = webSocketSessionMap.get(userId);

        if (user != null && user.getAttributes().get("userId").equals(userId)) {
            logger.info("============================== attributes" + user.getAttributes().get("userId") + "=========================");
            try {
                user.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

