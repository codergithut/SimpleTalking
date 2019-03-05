package com.tianjian.kafka.receiver;

import com.tianjian.kafka.bean.Message;
import com.tianjian.kafka.config.CommonConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
@KafkaListener(topics = {"#{commonConfig.topic}"})
@Component
class MultiListenerBean {

    @KafkaHandler
    public void listen(String record) {
        System.out.println("receive string " + record);
    }
//
//    @KafkaHandler
//    public void listen(Integer bar) {
//        ...
//    }

    @KafkaHandler(isDefault = true)
    @SendTo("receive")
    public String listenDefault(ConsumerRecord<?, Message> record) {
        Optional<Message> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Message message = kafkaMessage.get();
            System.out.println("receive message " + message.getMsg());

        }

        return "message is detail";

    }

}
