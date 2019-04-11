package com.tianjian.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.domain.model.TalkingContent;
import com.tianjian.websocket.handle.WebSocketPushHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: com.tianjian.websocket.controller
 * @Description: 后门测试接口
 * @Author: tianjian
 * @CreateDate: 2019/4/10
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/4/10
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/back")
public class BackRestController {

    @Autowired
    private WebSocketPushHandler webSocketPushHandler;

    @PostMapping("/mockSendMessage")
    public void sendMockMessage(@RequestBody TalkingContent talkingContent) {
        webSocketPushHandler.sendMessageToUser(talkingContent, "websocket");
    }

    public static void main(String[] args) {
        TalkingContent talkingContent = new TalkingContent();
        talkingContent.setSign("hahha");
        talkingContent.setConsume(false);
        talkingContent.setId(UUID.randomUUID().toString());
        talkingContent.setContent("this is test");
        talkingContent.setCreateDate(new Date());
        talkingContent.setFromId("haha");
        talkingContent.setToId("hahah");
        talkingContent.setType("mms");
        System.out.println(JSONObject.toJSONString(talkingContent));
    }



}
