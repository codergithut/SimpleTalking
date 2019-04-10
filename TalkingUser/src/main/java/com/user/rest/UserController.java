package com.user.rest;

import com.alibaba.fastjson.JSONObject;
import com.user.domain.entity.UserInfo;
import com.user.service.UserOpts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * redis服务监控 防止redis服务挂了
 * @ProjectName: com.session.manager.rest
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserOpts userOpts;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserInfo userInfo) {
        userOpts.addUser(userInfo);
    }

    @GetMapping("/getAll")
    public List<UserInfo> getUser() {
        return userOpts.getAllUserInfo();
    }

}
