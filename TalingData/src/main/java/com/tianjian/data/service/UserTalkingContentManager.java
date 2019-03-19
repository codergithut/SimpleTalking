package com.tianjian.data.service;

import com.tianjian.data.model.entity.user.TalkingContent;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: com.tianjian.data.service
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/19
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/19
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public interface UserTalkingContentManager {

    /**
     * 添加用户聊天记录信息
     * @param talkingContent
     * @return
     */
    boolean saveUserTalkingContent(TalkingContent talkingContent);

    /**
     * 根据用户聊天的记录统计
     * @param sign
     * @return
     */
    List<TalkingContent> getTalkingContentByUserIdTody(String sign);

}
