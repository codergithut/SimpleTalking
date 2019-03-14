package com.tianjian.data.model.service;

/**
 * @ProjectName: com.tianjian.data.model.service
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/14
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/14
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class UserInfo {


    private String userName;

    private String userId;

    private String tag;

    private String active;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
