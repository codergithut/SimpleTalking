package com.tianjian.data.service.impl;

import com.tianjian.data.model.entity.relation.FriendRelation;
import com.tianjian.data.model.entity.relation.GroupRelation;
import com.tianjian.data.model.entity.relation.TalkingGroup;
import com.tianjian.data.model.entity.relation.TalkingUser;
import com.tianjian.data.model.rep.neo4j.FriendRelationRepository;
import com.tianjian.data.model.rep.neo4j.GroupRelationRepository;
import com.tianjian.data.model.rep.neo4j.TalkingGroupRepository;
import com.tianjian.data.model.rep.neo4j.TalkingUserRepository;
import com.tianjian.data.model.service.FriendRelationServiceModel;
import com.tianjian.data.model.service.GroupInfo;
import com.tianjian.data.service.UserManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @ProjectName: com.tianjian.data.service.impl
 * @Description: group 对象为图数据库对象, talkingUser为图数据库和关系型数据共有数据, 关系数据也是图数据库特有数据
 * @Author: tianjian
 * @CreateDate: 2019/3/14
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/14
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    TalkingUserRepository talkingUserRepository;

    @Autowired
    FriendRelationRepository friendRelationRepository;

    @Autowired
    TalkingGroupRepository talkingGroupRepository;

    @Autowired
    GroupRelationRepository groupRelationRepository;

    @Override
    public boolean addTalkingUser(TalkingUser userEntity) {
        boolean existUser = talkingUserRepository.existsByUserId(userEntity.getUserId());
        TalkingUser talkingUser = new TalkingUser();
        BeanUtils.copyProperties(userEntity, talkingUser);
        if(!existUser) {
            talkingUserRepository.save(talkingUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTalkingUser(String userId) {
        boolean exist = talkingUserRepository.existsByUserId(userId);
        if(exist) {
            talkingUserRepository.deleteByUserId(userId);
            return true;
        }
        return false;

    }

    @Override
    public boolean addFriend(FriendRelationServiceModel serviceModel) {
        FriendRelation friendRelation = getUserInfo(serviceModel);
        if(!friendRelation.isValid()) {
            if(friendRelationRepository.getFriendRelationByUserInfo(serviceModel.getFrom(), serviceModel.getTo()) == null) {
                friendRelationRepository.save(friendRelation);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteFriend(FriendRelationServiceModel serviceModel) {
        FriendRelation friendRelation = friendRelationRepository.getFriendRelationByUserInfo(serviceModel.getFrom(), serviceModel.getTo());
        if(friendRelation != null) {
            friendRelationRepository.delete(friendRelation);
            return true;
        }
        return false;

    }

    @Override
    public boolean createGroup(GroupInfo groupInfo) {
        TalkingGroup talkingGroup = new TalkingGroup();
        groupInfo.setTag(UUID.randomUUID().toString());
        BeanUtils.copyProperties(groupInfo, talkingGroup);
        talkingGroupRepository.save(talkingGroup);
        return true;
    }

    @Override
    public boolean deleteGroup(Long groupId, String master) {
        Optional<TalkingGroup> talkingGroup = talkingGroupRepository.findById(groupId);
        if(talkingGroup.isPresent()) {
            if(talkingGroup.get().getMasters().contains(master)) {
                groupRelationRepository.deleteGroupRelationByTag(talkingGroup.get().getTag());
                talkingGroupRepository.deleteByTag(talkingGroup.get().getTag());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteSomeOneAtGroup(Long groupId, String userId) {
        Optional<TalkingGroup> talkingGroup = talkingGroupRepository.findById(groupId);
        if(talkingGroup.isPresent()) {
            GroupRelation groupRelation = groupRelationRepository.getGroupRelationByPeopleAndGroupId(userId, talkingGroup.get().getTag());
            if(groupRelation != null) {
                groupRelationRepository.deleteById(groupRelation.getId());
                return false;
            }
        }

        return false;
    }

    private FriendRelation getUserInfo(FriendRelationServiceModel friendRelationServiceModel) {
        FriendRelation friendRelation = new FriendRelation();
        TalkingUser from_ = talkingUserRepository.findByUserId(friendRelationServiceModel.getFrom());
        TalkingUser to_ = talkingUserRepository.findByUserId(friendRelationServiceModel.getTo());
        friendRelation.setFrom(from_);
        friendRelation.setTo(to_);
        return friendRelation;
    }
}
