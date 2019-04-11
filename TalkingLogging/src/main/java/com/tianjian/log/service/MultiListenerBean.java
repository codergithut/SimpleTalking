package com.tianjian.log.service;

import com.alibaba.fastjson.JSONObject;
import com.common.domain.model.TalkingContent;
import com.tianjian.log.domain.TalkingContentLog;
import com.tianjian.log.domain.TalkingContentLogJpaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName: com.tianjian.log
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@KafkaListener(topics = {"talkingContentLog"})
@Component
class MultiListenerBean {

    @Autowired
    private TalkingContentLogJpaRepository talkingContentLogJpaRepository;

    @KafkaHandler
    public void listen(String record) {
        System.out.println(record);
        TalkingContent talkingContent = JSONObject.parseObject(record, TalkingContent.class);
        TalkingContentLog talkingContentLog = new TalkingContentLog();
        BeanUtils.copyProperties(talkingContent, talkingContentLog);
//        if(talkingContent.isConsume()) {
//            talkingContentLog.setConsumeDate(new Date());
//        }
        TalkingContentLog s = talkingContentLogJpaRepository.save(talkingContentLog);
        System.out.println(s.getContent());
    }

}