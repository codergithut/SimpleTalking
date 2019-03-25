package com.tianjian.data.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: com.tianjian.data.task
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Component
public class HistoryContentCollection {

    private static final Logger log = LoggerFactory.getLogger(HistoryContentCollection.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
