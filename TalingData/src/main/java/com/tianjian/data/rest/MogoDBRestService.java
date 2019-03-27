package com.tianjian.data.rest;

import com.common.util.StringSortUtil;
import com.tianjian.data.domain.model.entity.log.TalkingContentHistory;
import com.tianjian.data.domain.model.entity.user.TalkingContent;
import com.tianjian.data.domain.model.rep.mongodb.TalkingContentHistroyResp;
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
@RequestMapping("/mg")
public class MogoDBRestService {

    @Autowired
    private TalkingContentHistroyResp mongoTest;

    @GetMapping("/getMongo")
    public List<TalkingContentHistory> getMogoDbInfo() {
        TalkingContentHistory talkingContentHistory = new TalkingContentHistory();
        List<TalkingContent> contents = new ArrayList<>();
        TalkingContent talkingContent = new TalkingContent();
        String fromId = UUID.randomUUID().toString();
        String toId = UUID.randomUUID().toString();
        talkingContent.setFromId(fromId);
        talkingContent.setToId(toId);
        talkingContent.setType("normal");
        talkingContent.setContent("this is test content");
        talkingContent.setCreateDate(new Date());
        contents.add(talkingContent);

        String[] ids = new String[]{fromId, toId};
        Arrays.sort(ids);
        talkingContent.setId(UUID.randomUUID().toString());
        talkingContentHistory.setHistory(new Date());
        talkingContentHistory.setSign(StringSortUtil.getKeyByKeys(ids));
        talkingContentHistory.setTalkingContents(contents);
        mongoTest.savePeople(talkingContentHistory);


        return mongoTest.getMogoHistoryByKey(fromId, new Date(0L), new Date());
    }
}
