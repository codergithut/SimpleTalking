package com.common.domain;

/**
 * @ProjectName: com.common.domain
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/27
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/27
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class TopicRegisterInfo {

    /**
     * kafka监听队列名称
     */
    private String topic;

    /**
     * 用户id
     */
    private String userId;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
