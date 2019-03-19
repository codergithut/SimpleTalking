package com.tianjian.data.model.rep.mongodb;

import com.tianjian.data.model.entity.log.TalkingContentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.data.model.rep.mongodb
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class TalkingContentHistroyResp {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加聊天日志记录 定时任务处理
     * @param talkingContentHistory
     */
    public void savePeople(TalkingContentHistory talkingContentHistory) {
        mongoTemplate.save(talkingContentHistory);
    }

    /**
     * 获取所有用户聊天数据
     * @return
     */
    public List<TalkingContentHistory> getMogoPerson() {
        return mongoTemplate.findAll(TalkingContentHistory.class, "talkingContentHistory");
    }

    /**
     * 根据时间查找用户聊天记录
     * @param key 用户key 通过正则匹配
     * @param time 用户查询时间
     * @return
     */
    public List<TalkingContentHistory> getMogoHistoryByKey(String key, Date time) {
        Query query = new Query();
        query.addCriteria(Criteria.where("history").lt(time));
        query.addCriteria(Criteria.where("sign").regex(key));
        return mongoTemplate.find(query, TalkingContentHistory.class, "talkingContentHistory");
    }
}
