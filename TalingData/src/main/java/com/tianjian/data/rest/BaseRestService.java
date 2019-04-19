package com.tianjian.data.rest;

import com.common.util.StringSortUtil;
import com.tianjian.data.domain.model.entity.user.TalkingContent;
import com.tianjian.data.domain.model.entity.user.TalkingContentJpaRepository;
import com.user.domain.entity.UserInfo;
import com.user.domain.entity.UserJpaRepository;
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

    @Autowired
    private TalkingContentJpaRepository talkingContentJpaRepository;

    @GetMapping("/saveUser")
    public List<TalkingContent> getMogoDbInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setBakInfo("ahah");
        userInfo.setEmail("ssd");
        userInfo.setName("haha");
        userInfo.setPassword("tj");
        userInfo.setSign("sign");
        userJpaRepository.save(userInfo);
        TalkingContent talkingContent = new TalkingContent();
        talkingContent.setContent("我是测试数据");
        talkingContent.setCreateDate(new Date());
        String fromId = UUID.randomUUID().toString();
        String toId = UUID.randomUUID().toString();
        talkingContent.setFromId(fromId);
        talkingContent.setToId(toId);
        String[] keys = new String[]{fromId, toId};
        String key = StringSortUtil.getKeyByKeys(keys);
        talkingContent.setSign(key);
        talkingContent.setId(UUID.randomUUID().toString());
        talkingContentJpaRepository.save(talkingContent);
        List<TalkingContent> datas = talkingContentJpaRepository.findBySignAndCreateDateBetween(key, new Date(0L), new Date());
        return datas;
    }

}
