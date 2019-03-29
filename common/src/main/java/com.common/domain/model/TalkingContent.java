package com.common.domain.model;

import java.util.Date;

/**
 * @ProjectName: com.tianjian.data.model.entity.talk
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */

public class TalkingContent {

    private String id;

    private String sign;

    private String fromId;

    private String toId;

    private String content;

    private String type;

    private Date createDate;

    private boolean consume;


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isConsume() {
        return consume;
    }

    public void setConsume(boolean consume) {
        this.consume = consume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
