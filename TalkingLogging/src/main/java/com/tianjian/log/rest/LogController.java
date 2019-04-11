package com.tianjian.log.rest;

import com.tianjian.log.domain.TalkingContentLog;
import com.tianjian.log.domain.TalkingContentLogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getLog")
    public List<TalkingContentLog> getTalkingContentLogById(String userId) {
        return talkingContentLogJpaRepository.findByToIdAndConsume(userId, "false");
    }

    @GetMapping("/getAllLog")
    public List<TalkingContentLog> getTalkingContent() {
        return talkingContentLogJpaRepository.findAll();
    }


}
