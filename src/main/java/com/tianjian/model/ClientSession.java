package com.tianjian.model;

/**
 * Created by tianjian on 2019/3/4.
 */
public class ClientSession {
    /**
     * 用户id
     */
    private String userId;

    /**
     * jwt用户Token 验证用户信息是否正确
     */
    private String jwtToken;

    /**
     * sessionId 是判断对话是否存活
     */
    private String sessionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
