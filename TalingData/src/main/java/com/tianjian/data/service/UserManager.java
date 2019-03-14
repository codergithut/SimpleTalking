package com.tianjian.data.service;

import com.tianjian.data.model.service.FriendRelationServiceModel;
import com.tianjian.data.model.service.GroupInfo;
import com.tianjian.data.model.service.UserInfo;

/**
 * @ProjectName: com.tianjian.data.service
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/14
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/14
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public interface UserManager {

    boolean addTalkingUser(UserInfo userInfo);

    boolean deleteTalkingUser(String userId);

    boolean addFriend(FriendRelationServiceModel friendRelationServiceModel);

    boolean deleteFriend(FriendRelationServiceModel serviceModel);

    boolean createGroup(GroupInfo groupInfo);

    boolean deleteGroup(Long gourpId, String userId);

    boolean deleteSomeOneAtGroup(Long groupId, String userId);
}
