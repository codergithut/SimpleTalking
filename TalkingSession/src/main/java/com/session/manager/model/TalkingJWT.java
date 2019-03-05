package com.session.manager.model;

import java.util.List;

/**
 * @ProjectName: com.session.manager.model
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class TalkingJWT {

    private String token;

    private String userId;

    private String userName;

    private String[] audiences;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getAudiences() {
        return audiences;
    }

    public void setAudiences(String[] audiences) {
        this.audiences = audiences;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
