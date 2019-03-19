package com.tianjian.data.model.entity.log;

import com.tianjian.data.model.entity.talk.TalkingContent;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.data.model.entity.log
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Document
public class TalkingContentHistory {

    /**
     * 历史记录时间
     */
    @Field("history")
    private Date history;


    /**
     * 聊天内容
     */
    @Field("talkingContents")
    private List<TalkingContent> talkingContents;

    /**
     * 对话用户id按照字典排序 中间用-隔开 作为查询依据
     */
    @Field("sign")
    private String sign;

    public Date getHistory() {
        return history;
    }

    public void setHistory(Date history) {
        this.history = history;
    }

    public List<TalkingContent> getTalkingContents() {
        return talkingContents;
    }

    public void setTalkingContents(List<TalkingContent> talkingContents) {
        this.talkingContents = talkingContents;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
