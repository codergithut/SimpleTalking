package com.session.manager.security.config;

import com.common.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: com.security.config
 * @Description: MD5 加密类用于签名用户密码来和数据库比对
 * @Author: tianjian
 * @CreateDate: 2019/1/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class Md5PasswordEncoder implements PasswordEncoder {

    private Logger logger = LoggerFactory.getLogger(Md5PasswordEncoder.class);

    @Value("${jwt.md5-key}")
    private String md5Key;

    /**
     * 对用户密码进行加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        MD5Util.setKey(md5Key);
        try {
            return MD5Util.getMD5Pass(rawPassword.toString());
        } catch (Exception e) {
            logger.error("MD5 encode fail ", e);
            return null;
        }
    }

    /**
     * 判断用户密码和数据库查询的密码是否一致
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("========================" + encodedPassword + "========================");
        System.out.println("========================" + encode(rawPassword) + "========================");
        if(encodedPassword.equals(encode(rawPassword))) {
            return true;
        }
        return false;
    }
}
