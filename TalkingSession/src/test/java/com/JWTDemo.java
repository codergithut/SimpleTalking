package com;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @ProjectName: com
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/4
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/4
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class JWTDemo {

    public String createTokenWithClaim() {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
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
                    .withClaim("loginName", "lijunkui")
                    .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("this is test token")//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("APP")//签名的观众 也可以理解谁接受签名的
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

    public void verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("SERVICE")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String subject = jwt.getSubject();
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("loginName");
            System.out.println(claim.asString());
            List<String> audience = jwt.getAudience();
            System.out.println(subject);
            System.out.println(audience.get(0));
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JWTDemo demo = new JWTDemo();
        String createTokenWithClaim = demo.createTokenWithClaim();
        demo.verifyToken(createTokenWithClaim);
    }
}

