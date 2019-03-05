package com.tianjian.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @ProjectName: api-micro-service
 * @Package: com.kafka.config
 * @Description:
 * @Author: tianjian
 * @CreateDate: 2019/2/25
 * @UpdateUser: Carl Yu
 * @UpdateDate: 2019/2/25
 * @UpdateRemark: tianjian
 * @Version: v1.0
 */
@Configuration
@EnableKafka
public class KafkaConfig implements KafkaListenerConfigurer {


    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
//        registrar.setValidator(new MyValidator());
    }

    public static class MyValidator implements Validator {

        @Override
        public boolean supports(Class<?> aClass) {
            if(aClass ==  ConsumerRecord.class) {
                return true;
            }
            return false;
        }

        @Override
        public void validate(Object o, Errors errors) {

            System.out.println("data sssssssssss=================================");
            if(o instanceof ConsumerRecord) {
            }

        }
    }
}
