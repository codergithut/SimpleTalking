package com.tianjian.data.domain.model.service;


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
public class GroupInfo {

    private Long id;

    private String groupName;

    private String tag;

    private String createId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}
