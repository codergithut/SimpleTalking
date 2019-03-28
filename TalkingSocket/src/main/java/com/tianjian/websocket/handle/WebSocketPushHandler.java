package com.tianjian.websocket.handle;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.kafka.bean.Message;
import com.tianjian.redis.service.UserTopicInfo;
import com.tianjian.websocket.model.TalkingContent;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
        String userId = talkingContent.getToId();
        for (WebSocketSession user : userList) {
            if (user.getAttributes().get("userId").equals(userId)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(new TextMessage(talkingContent.getContent()));
                        saveTalkingContentingLog(talkingContent,"1");
                        return ;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    saveTalkingContentingLog(talkingContent,"0");
                    return ;
                }
            }
        }

        /**
         * 如果本地能找到需要发送的session本机发送,否则通过消息中间件发送消息
         */
        if(!"kafka".equals(type)) {
            String topic =  userTopicInfo.getTopicByUserId(talkingContent.getToId());
            if(StringUtils.isBlank(topic)) {
                saveTalkingContentingLog(talkingContent, "0");
                return ;
            }
            kafkaTemplate.send(topic, JSONObject.toJSONString(talkingContent));
            return ;
        } else {
            saveTalkingContentingLog(talkingContent, "0");
        }

    }

    private void saveTalkingContentingLog(TalkingContent talkingContent, String status) {
        //todo 消息存储到数据库 包含消息内容和状态,是否发送成功的状态 异步发送到日志服务器,保存已经发送的和未能成功发送的消息数据
        return ;
    }


}

