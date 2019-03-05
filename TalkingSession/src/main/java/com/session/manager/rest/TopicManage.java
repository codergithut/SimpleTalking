package com.session.manager.rest;

import com.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: com.session.manager.rest
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/topic")
public class TopicManage {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户id 和 topic绑定
     * @param topicName
     * @param userId
     * @return
     */
    @PostMapping("/register")
    public ResponseMessage addTopicAndUserIdRelation(String topicName, String userId) {
        boolean flag = redisTemplate.hasKey(userId);
        if(!flag) {
            redisTemplate.opsForValue().set(userId, topicName);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(0);
        responseMessage.setMessage("register success");
        return responseMessage;
    }

    /**
     * 用户离开需要解绑用户和服务器关系
     * @param userId
     * @return
     */
    @PostMapping("/unRegister")
    public ResponseMessage removeTopicAndUserIdRelation(String userId) {
        boolean flag = redisTemplate.hasKey(userId);
        if(flag) {
            redisTemplate.delete(userId);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(0);
        responseMessage.setMessage("register success");
        return responseMessage;
    }

    /**
     * serverName 和 topic 关系加入redis ,服务器宕机,可以根据serverName定位topic 将消息分流
     * @param serverName
     * @param topicName
     * @return
     */
    @PostMapping("/registerServer")
    public ResponseMessage registerSrver(String serverName, String topicName) {
        boolean flag = redisTemplate.hasKey(serverName);
        if(!flag) {
            redisTemplate.opsForValue().set(serverName, topicName);
        }
        return new ResponseMessage();
    }


}
