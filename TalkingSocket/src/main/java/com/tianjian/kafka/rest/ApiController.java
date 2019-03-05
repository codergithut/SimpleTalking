package com.tianjian.kafka.rest;

import com.tianjian.kafka.bean.Message;
import com.tianjian.kafka.config.CommonConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;


/**
 * @ProjectName: api-micro-service
 * @Package: com.kafka.rest
 * @Description:
 * @Author: tianjian
 * @CreateDate: 2019/2/25
 * @UpdateUser: Carl Yu
 * @UpdateDate: 2019/2/25
 * @UpdateRemark: tianjian
 * @Version: v1.0
 */
@RestController
public class ApiController {

    @Autowired
    private KafkaTemplate<Integer, Message> template;

    @Autowired
    CommonConfig commonConfig;

    @GetMapping("kafka")
    public String kafkaTest() {

        Message message = new Message();
        message.setId(1L);
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());


        System.out.println("--------------------" + commonConfig.getTopic() + "--------------------");
        final ProducerRecord<Integer, Message> record = createRecord(message, commonConfig.getTopic());

        ListenableFuture<SendResult<Integer, Message>> future = template.send(record);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Message>>() {

            @Override
            public void onSuccess(SendResult<Integer, Message> result) {
                handleSuccess(result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("=========================================");
                handleFailure(ex);
            }

        });

        System.out.println("===================================" + commonConfig.getTopic() + "===================================");

        return "success";


    }

    public static ProducerRecord<Integer,Message> createRecord(Message message, String topic) {
        ProducerRecord<Integer,Message> producerRecord = new ProducerRecord<Integer,Message>(topic, message);
        return producerRecord;
    }

    public void handleSuccess(SendResult<Integer, Message> result) {
        System.out.println("message is comsumer " + result.getProducerRecord().value().getMsg());
    }

    public void handleFailure(Throwable ex) {
        System.out.println(ex.getCause().getLocalizedMessage());
    }



}
