package com.tianjian.kafka.receiver;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * @ProjectName: api-micro-service
 * @Package: com.kafka.receiver
 * @Description:
 * @Author: tianjian
 * @CreateDate: 2019/2/26
 * @UpdateUser: Carl Yu
 * @UpdateDate: 2019/2/26
 * @UpdateRemark: tianjian
 * @Version: v1.0
 */
@KafkaListener(topics = "receive")
@Component
class ReciveListenerBean {

    @KafkaHandler
    public void listen(String record) {
        System.out.println("receive string " + record);
    }

}
