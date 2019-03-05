package com.session.manager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.session.manager.model.view.UserInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: com.session.manager
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class JWTUtil {

    public static String createTokenWithClaim(UserInfo userInfo, String secretKey, String[] audiences) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            map.put("alg", "HS256");
            map.put("type", "JWT");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expire = now.plusHours(2);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = expire.atZone(zone).toInstant();
            Date expireDate = Date.from(instant);
            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim("userId", userInfo.getUserId())
                    .withClaim("userName", userInfo.getUserName())
                    .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("JWT token")//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience(audiences)//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
