package com.user.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.user.domain.entity.UserInfo;
import com.user.security.filter.MyUsernamePasswordAuthenticationFilter;
import com.user.service.UserOpts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

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

	@Autowired
	private UserOpts userOpts;



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
	 * @param userId
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<UserInfo> data = userOpts.getUserInfoByUserId(Long.valueOf(userId));
		return User.builder().username(userId)
				.password(data.get().getPassword())
				.roles("USER").build();
	}

}
