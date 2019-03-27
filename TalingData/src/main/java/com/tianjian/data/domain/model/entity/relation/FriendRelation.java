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
@RelationshipEntity(type = "FRIENDS")
public class FriendRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private TalkingUser from;

    @EndNode
    private TalkingUser to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TalkingUser getFrom() {
        return from;
    }

    public void setFrom(TalkingUser from) {
        this.from = from;
    }

    public TalkingUser getTo() {
        return to;
    }

    public void setTo(TalkingUser to) {
        this.to = to;
    }

    public boolean isValid() {
        if(from != null & to != null) {
            return true;
        }
        return false;
    }
}
