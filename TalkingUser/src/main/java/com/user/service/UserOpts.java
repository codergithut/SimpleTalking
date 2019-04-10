package com.user.service;

import com.user.domain.entity.UserInfo;
import com.user.domain.entity.UserJpaRepository;
import com.user.security.config.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @ProjectName: com.user.service
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/4/10
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/4/10
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class UserOpts {

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Autowired
    UserJpaRepository userJpaRepository;


    public UserInfo addUser(UserInfo userInfo) {
        if(StringUtils.isEmpty(userInfo.getId())) {
            userInfo.setId(UUID.randomUUID().toString());
        }
        if(!StringUtils.isEmpty(userInfo.getPassword())) {
            userInfo.setPassword(md5PasswordEncoder.encode(userInfo.getPassword()));
        }
        return userJpaRepository.save(userInfo);
    }

    public List<UserInfo> getAllUserInfo() {
        return userJpaRepository.findAll();
    }

    public void removeUserById(String id) {
        userJpaRepository.deleteById(id);
    }

    public void removeAllUser() {
        userJpaRepository.deleteAll();
    }

    public Optional<UserInfo> getUserInfoByUserId(String id) {
        return userJpaRepository.findById(id);
    }
}
