package com.tianjian.websocket.handle;

import com.alibaba.fastjson.JSONObject;
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
 * Created by tianjian on 2019/3/4.
 */
@Service
public class WebSocketPushHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final List<WebSocketSession> userList = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private UserTopicInfo userTopicInfo;


    /**
     * 用户进入系统监听
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("xxx用户进入系统。。。");
        logger.info("用户信息:" + session.getAttributes());
        Map<String, Object> map = session.getAttributes();
        for (String key : map.keySet()) {
            logger.info("key:" + key + " and value:" + map.get(key));
        }
        userList.add(session);
    }

    /**
     * 处理用户请求
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        TalkingContent talkingContent = JSONObject.parseObject(message.getPayload(), TalkingContent.class);
        TalkingContent talkingContent = new TalkingContent();
        talkingContent.setContent("this is test");
        talkingContent.setCreateDate(new Date());
        talkingContent.setFromId("tj");
        talkingContent.setToId("tj1");
        talkingContent.setType("sms");
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
        userList.remove(session);
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
        /**
         * 尝试发送消息到客户端
         */
        for (WebSocketSession user : userList) {
            if (user.getAttributes().get("userId").equals(userId)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(new TextMessage(talkingContent.getContent()));
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
         * 如果本地能找到需要发送的session本机发送,否则通过消息中间件发送消息
         */
        if(!"kafka".equals(type)) {
            String topic =  userTopicInfo.getTopicByUserId(talkingContent.getToId());
            if(!StringUtils.isBlank(topic)) {
                kafkaTemplate.send(topic, JSONObject.toJSONString(talkingContent));
                return ;
            }
        }

        saveTalkingContentingLog(talkingContent);
        return;

    }

    /**
     * 保存日志到数据库
     * @param talkingContent 聊天日志
     */
    private void saveTalkingContentingLog(TalkingContent talkingContent) {
        kafkaTemplate.send("talkingContentLog", JSONObject.toJSONString(talkingContent));
        return ;
    }


}

