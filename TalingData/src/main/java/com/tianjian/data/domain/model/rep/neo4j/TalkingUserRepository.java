package com.tianjian.data.domain.model.rep.neo4j;

import com.tianjian.data.domain.model.entity.relation.TalkingUser;
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
public interface TalkingUserRepository extends Neo4jRepository<TalkingUser, Long> {

    TalkingUser findByUserId(String userId);

    void deleteByUserId(String userId);

    boolean existsByUserId(String userId);
}
