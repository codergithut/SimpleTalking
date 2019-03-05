package com.session.manager.rest;

import com.session.manager.model.TalkingJWT;
import com.session.manager.model.VerifierKey;
import com.session.manager.model.view.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static com.session.manager.util.JWTUtil.createTokenWithClaim;

/**
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
@RequestMapping("/login")
public class LoginController {


    @Value("${oauth2.secret}")
    private String secretKey;

    @PostMapping("/getJWT")
    public TalkingJWT getTalkingToken(@RequestBody UserInfo userinfo) {
        String[] audiences = new String[]{"Talking"};
        TalkingJWT talkingJWT = new TalkingJWT();
        //todo 验证用户是否正确
        talkingJWT.setToken(createTokenWithClaim(userinfo, secretKey, audiences));
        talkingJWT.setAudiences(audiences);
        talkingJWT.setUserId(userinfo.getUserId());
        return talkingJWT;

    }

    @GetMapping("/getKey")
    public VerifierKey getVerifierKey() {
        return new VerifierKey(secretKey);
    }
}
