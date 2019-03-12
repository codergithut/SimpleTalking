package com.tianjian.data.model.rep;

import com.tianjian.data.model.entity.relation.TalkingUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @ProjectName: com.tianjian.data.model.rep
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/12
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/12
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public interface TalkingUserRepository extends Neo4jRepository<TalkingUser, Long> {

    @Query("match( p1: user {userName:{0}} )-[rel:FRIENDS]->(p2) return p2")
    List<TalkingUser> getTalkingUserFriend(String userName);

    @Query("MATCH (p:user)-[r:GROUP]->(g:group{groupName:{0}}) RETURN p ")
    List<TalkingUser> getTalkingUserByGroup(String groupName);


}
