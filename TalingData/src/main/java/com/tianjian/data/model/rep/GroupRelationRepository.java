package com.tianjian.data.model.rep;

import com.tianjian.data.model.entity.relation.GroupRelation;
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
public interface GroupRelationRepository extends Neo4jRepository<GroupRelation, Long> {

    @Query("MATCH ()-[r:GROUP]->(g:group{tag:{0}}) DELETE r")
    void deleteGroupRelationByTag(String tag);

    @Query("MATCH (u:user{userId:{0}})-[r:GROUP]->(g:group{tag:{1}}) RETURN r")
    GroupRelation getGroupRelationByPeopleAndGroupId(String userId, String tag);

    @Query("MATCH (u:user{userId:{0}})-[r:GROUP]->(g:group{tag:{1}}) DELETE r")
    void deleteGroupRelationByPeopleAndGroupId();


}
