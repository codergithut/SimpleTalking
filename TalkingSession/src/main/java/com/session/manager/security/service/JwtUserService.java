package com.session.manager.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.session.manager.security.filter.MyUsernamePasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ProjectName: com.security.security.service
 * @Description: JWT 安全验证服务类
 * @Author: tianjian
 * @CreateDate: 2019/1/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class JwtUserService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUsernamePasswordAuthenticationFilter.class);

	@Value("${jwt.key}")
	private String jwtKey;



	/**
	 * 创建JWT token消息
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String createJWT(UserDetails user) throws UnsupportedEncodingException {
		Algorithm algorithm = Algorithm.HMAC256(jwtKey);
		Date date = new Date(System.currentTimeMillis()+36000*10000);  //设置1小时后过期
        return JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.builder().username(username)
				.password("60d19ba08d1d40ba2ffeded057616340")
				.roles("USER").build();
	}

}
