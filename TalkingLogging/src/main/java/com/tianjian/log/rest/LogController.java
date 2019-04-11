package com.tianjian.log.rest;

import com.alibaba.fastjson.JSONObject;
import com.common.domain.model.TalkingContent;
import com.common.util.DateUtil;
import com.tianjian.log.domain.TalkingContentLog;
import com.tianjian.log.domain.TalkingContentLogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.log.rest
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/4/10
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/4/10
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RequestMapping("/logging")
@RestController
public class LogController {

    @Autowired
    private TalkingContentLogJpaRepository talkingContentLogJpaRepository;

    @GetMapping("/getUnReadInfoLog")
    public List<TalkingContentLog> getUnReadInfo(@RequestParam(value="userId") String userId,
                                                 @RequestParam(value="consume") boolean consume) {
        return talkingContentLogJpaRepository.findByToIdAndConsume(userId, consume);
    }

    @PostMapping("/updateLonInfo")
    public void updateLogInfo(@RequestBody List<String> ids) {
        List<TalkingContentLog> datas = talkingContentLogJpaRepository.findAllById(ids);
        for(TalkingContentLog talkingContentLog : datas) {
            talkingContentLog.setConsume(true);
            talkingContentLogJpaRepository.save(talkingContentLog);
//            talkingContentLog.setConsumeDate(new Date());
        }

    }

    @GetMapping("/getLogByDay")
    public List<TalkingContentLog> getTalkingContentLogByDate(@RequestParam(value="day") String date,
                                                              @RequestParam(value="userId") String userId) {
        List<TalkingContentLog> allLogs = new ArrayList<TalkingContentLog>();
        Date start = DateUtil.getStartOfDay(date);
        Date end = DateUtil.getEndOfDay(date);
        List<TalkingContentLog> from = talkingContentLogJpaRepository.findByFromId(userId, start, end);
        List<TalkingContentLog> to = talkingContentLogJpaRepository.findByToId(userId, start, end);
        if(CollectionUtils.isEmpty(from)) {
            allLogs.addAll(from);
        }
        if(CollectionUtils.isEmpty(to)) {
            allLogs.addAll(to);
        }
        return allLogs;

    }

    @GetMapping("/getAllLog")
    public List<TalkingContentLog> getTalkingContent() {
        return talkingContentLogJpaRepository.findAll();
    }

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("test");
        System.out.println(JSONObject.toJSONString(ids));
    }


}
