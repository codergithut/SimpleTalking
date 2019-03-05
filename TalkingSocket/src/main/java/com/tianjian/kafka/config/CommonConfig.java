package com.tianjian.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: com.tianjian.kafka.config
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/5
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/5
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Component
@Service
public class CommonConfig {

    @Value("${kafka.topic}")
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
