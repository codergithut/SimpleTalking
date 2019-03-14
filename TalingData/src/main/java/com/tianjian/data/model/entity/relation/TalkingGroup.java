package com.tianjian.data.model.entity.relation;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @ProjectName: com.tianjian.data.model.entity.relation
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/12
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/12
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@NodeEntity(label = "group")
public class TalkingGroup {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "groupName")
    private String groupName;

    @Property(name = "tag")
    private String tag;

    @Property(name = "createId")
    private String createId;

    @Property(name = "masters")
    private String masters;


    /**
     * groupid 数据库中
     */
    @Property(name = "groupId")
    private String groupId;

    @Property(name = "description")
    private String description;

    @Property(name = "active")
    private String active;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getMasters() {
        return masters;
    }

    public void setMasters(String masters) {
        this.masters = masters;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
