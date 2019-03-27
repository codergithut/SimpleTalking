package com.tianjian.data.service.impl;

import com.tianjian.data.domain.model.entity.user.TalkingContent;
import com.tianjian.data.domain.model.entity.user.TalkingContentJpaRepository;
import com.tianjian.data.service.UserTalkingContentManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.data.service.impl
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class UserTalkingContentManagerImpl implements UserTalkingContentManager {

    @Autowired
    private TalkingContentJpaRepository talkingContentJpaRepository;

    @Override
    public boolean saveUserTalkingContent(TalkingContent talkingContent) {
        TalkingContent save = talkingContentJpaRepository.save(talkingContent);
        if(save == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<TalkingContent> getTalkingContentByUserIdTody(String sign) {
        return talkingContentJpaRepository.findBySignAndCreateDateBetween(sign, new Date(0), new Date());
     }
}
