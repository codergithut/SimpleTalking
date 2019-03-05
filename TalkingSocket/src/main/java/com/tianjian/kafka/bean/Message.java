package com.tianjian.kafka.bean;

import java.util.Date;

/**
 * @ProjectName: api-micro-service
 * @Package: com.kafka.bean
 * @Description:
 * @Author: tianjian
 * @CreateDate: 2019/2/25
 * @UpdateUser: Carl Yu
 * @UpdateDate: 2019/2/25
 * @UpdateRemark: tianjian
 * @Version: v1.0
 */
public class Message {

    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
