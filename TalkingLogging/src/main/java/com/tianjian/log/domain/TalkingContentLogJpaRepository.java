package com.tianjian.log.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.data.model.entity.user
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Repository
public interface TalkingContentLogJpaRepository extends JpaRepository<TalkingContentLog, String> {

    List<TalkingContentLog> findBySignAndCreateDateBetween(String sign, Date startTime, Date endTime);

    List<TalkingContentLog> findByToIdAndConsume(String toId, boolean consume);
}
