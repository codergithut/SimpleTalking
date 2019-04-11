package com.tianjian.log.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ProjectName: com.tianjian.log.domain
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Entity
@Table(name = "talking_log")
public class TalkingContentLog {

    /**
     * 记录id
     */
    @Id
    @Column(length=200)
    private String id;

    /**
     * 消息标识
     */
    private String sign;

    /**
     * 发送方
     */
    private String fromId;

    /**
     * 接收方
     */
    private String toId;

    /**
     * 文本信息
     */
    private String content;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否已发送
     */
    private boolean consume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
