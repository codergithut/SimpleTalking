package com.tianjian.data.model.rep;

import com.tianjian.data.model.entity.relation.FriendRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

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
public interface FriendRelationRepository extends Neo4jRepository<FriendRelation, Long> {
    @Query("MATCH (p:user{userName:{0}})-[r:FRIENDS]->(t:user{userName:{1}}) RETURN r")
    FriendRelation getFriendRelationByUserInfo(String from, String to);

    @Query("MATCH (p:user{userName:{0}})-[r:FRIENDS]->(t:user{userName:{1}}) DELETE r")
    void deleteFriendRelationByUserInfo(String from, String to);
}
