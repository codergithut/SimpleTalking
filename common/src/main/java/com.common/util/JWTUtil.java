package com.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.thymeleaf.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    public static String createTokenWithClaim(String userId, String secretKey, String[] audiences) {

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
                    .withClaim("userId", userId)
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


    public static String verifyToken(String token, String secret) {
        if(StringUtils.isEmpty(token)) {
            return token;
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        //todo 获取用户id
        String subject = jwt.getSubject();
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("sub");
        System.out.println("----------------------------" + claim.asString() + "----------------------------");
        return subject;
    }

}
