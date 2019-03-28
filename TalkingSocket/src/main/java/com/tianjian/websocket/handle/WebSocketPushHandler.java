package com.tianjian.websocket.handle;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.redis.service.UserTopicInfo;
import com.tianjian.websocket.model.TalkingContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
//        System.out.println();
//        TalkingContent talkingContent = JSONObject.parseObject(message.getPayload(), TalkingContent.class);
        TalkingContent talkingContent = new TalkingContent();
        talkingContent.setContent("this is test");
        talkingContent.setCreateDate(new Date());
        talkingContent.setFromId("tj");
        talkingContent.setToId("tj1");
        talkingContent.setType("sms");
        logger.info("系统处理xxx用户的请求信息。。。");
        String topic =  userTopicInfo.getTopicByUserId(talkingContent.getToId());
        kafkaTemplate.send(topic, JSONObject.toJSONString(talkingContent));
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
     * 给所有的在线用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : userList) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getLocalizedMessage());
            }
        }
    }

    /**
     * 自定义函数
     * 发送消息给指定的在线用户
     */
    public static void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession user : userList) {
            if (user.getAttributes().get("userId").equals(userId)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

