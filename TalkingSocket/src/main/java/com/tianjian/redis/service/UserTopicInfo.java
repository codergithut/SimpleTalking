package com.tianjian.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: com.tianjian.redis.service
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/27
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/27
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class UserTopicInfo {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean saveUserTopicInfo(String userId, String topic) {
        logger.info("save topic and user to redis userid={}, topic={}", userId, topic);
        if(redisTemplate.hasKey(userId)) {
            logger.warn("user has registed, userid = {}", userId);
            return false;
        }
        redisTemplate.opsForValue().set(userId, topic);
        return true;
    }

    public boolean removeUserTopicInfo(String userId) {
        logger.info("remove topic and user to redis userid={}, topic={}", userId);
        if(redisTemplate.hasKey(userId)) {
            redisTemplate.delete(userId);
            return true;
        } else {
            logger.warn("can not find user, userid = {}", userId);
            return false;
        }
    }

    public String getTopicByUserId(String userId) {
        if(redisTemplate.hasKey(userId)) {
            return redisTemplate.opsForValue().get(userId);
        } else {
            return null;
        }

    }

}
