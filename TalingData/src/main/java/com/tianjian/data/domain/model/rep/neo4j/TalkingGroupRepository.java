package com.tianjian.data.domain.model.rep.neo4j;

import com.tianjian.data.domain.model.entity.relation.TalkingGroup;
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
public interface TalkingGroupRepository extends Neo4jRepository<TalkingGroup, Long> {
    void deleteByTag(String tag);
}
