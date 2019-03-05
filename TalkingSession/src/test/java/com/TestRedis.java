package com;

import com.session.manager.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: com
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Component
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("test:set","testValue1");
    }
}

