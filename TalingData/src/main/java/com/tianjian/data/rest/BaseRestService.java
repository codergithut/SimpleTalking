package com.tianjian.data.rest;

import com.common.util.StringSortUtil;
import com.tianjian.data.model.entity.log.TalkingContentHistory;
import com.tianjian.data.model.entity.talk.TalkingContent;
import com.tianjian.data.model.entity.user.UserInfo;
import com.tianjian.data.model.entity.user.UserJpaRepository;
import com.tianjian.data.model.rep.mongodb.TalkingContentHistroyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ProjectName: com.tianjian.data.rest
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/mysql")
public class BaseRestService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("/saveUser")
    public UserInfo getMogoDbInfo() {
        TalkingContentHistory talkingContentHistory = new TalkingContentHistory();

        UserInfo userInfo = new UserInfo();
        userInfo.setBakInfo("ahah");
        userInfo.setEmail("ssd");
        userInfo.setName("haha");
        userInfo.setPassword("tj");
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setSign("sign");
        userJpaRepository.save(userInfo);

        return userJpaRepository.save(userInfo);
    }

}
