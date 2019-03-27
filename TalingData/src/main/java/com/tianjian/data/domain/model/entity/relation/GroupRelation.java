package com.tianjian.data.domain.model.entity.relation;

import org.neo4j.ogm.annotation.*;

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
@RelationshipEntity(type = "GROUP")
public class GroupRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private TalkingUser talkingUser;

    @EndNode
    private TalkingGroup talkingGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TalkingUser getTalkingUser() {
        return talkingUser;
    }

    public void setTalkingUser(TalkingUser talkingUser) {
        this.talkingUser = talkingUser;
    }

    public TalkingGroup getTalkingGroup() {
        return talkingGroup;
    }

    public void setTalkingGroup(TalkingGroup talkingGroup) {
        this.talkingGroup = talkingGroup;
    }
}
