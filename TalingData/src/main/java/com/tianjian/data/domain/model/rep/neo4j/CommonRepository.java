package com.tianjian.data.domain.model.rep.neo4j;

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
public interface CommonRepository extends Neo4jRepository {

    @Query("MATCH p=()-->() RETURN p LIMIT 25")
    List findAllRelation();

    @Query("MATCH p=()-->() delete p")
    void deleteAllRealtion();

    @Query("MATCH (n) RETURN n LIMIT 25")
    List findAllEntity();

    @Query("MATCH (n) delete n")
    void deleteAllEntity();



}
