package com.tianjian.data.service;

import com.tianjian.data.domain.model.entity.relation.TalkingUser;
import com.tianjian.data.domain.model.service.FriendRelationServiceModel;
import com.tianjian.data.domain.model.service.GroupInfo;

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

    /**
     * 添加用户节点
     * @param userEntity 请求用户信息
     * @return
     */
    boolean addTalkingUser(TalkingUser userEntity);

    /**
     * 删除用户节点
     * @param userId 用户id
     * @return
     */
    boolean deleteTalkingUser(String userId);

    /**
     * 添加好友关系
     * @param friendRelationServiceModel
     * @return
     */
    boolean addFriend(FriendRelationServiceModel friendRelationServiceModel);

    /**
     * 删除好友关系
     * @param serviceModel
     * @return
     */
    boolean deleteFriend(FriendRelationServiceModel serviceModel);

    /**
     * 创建群
     * @param groupInfo 群信息
     * @return
     */
    boolean createGroup(GroupInfo groupInfo);

    /**
     * 删除群信息
     * @param gourpId 群id
     * @param userId 删除用户id
     * @return
     */
    boolean deleteGroup(Long gourpId, String userId);

    /**
     * 群踢出某人
     * @param groupId 群id
     * @param userId 踢出人
     * @return
     */
    boolean deleteSomeOneAtGroup(Long groupId, String userId);
}
