package com.tianjian.kafka.sender;


import com.tianjian.kafka.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: api-micro-service
 * @Package: com.kafka.sender
 * @Description:
 * @Author: tianjian
 * @CreateDate: 2019/2/25
 * @UpdateUser: Carl Yu
 * @UpdateDate: 2019/2/25
 * @UpdateRemark: tianjian
 * @Version: v1.0
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("zhisheng", message.toString());
    }
}
