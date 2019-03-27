package com.session.manager.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * @ProjectName: com.security.security.filter
 * @Description: 用户安全密码验证过滤器
 * @Author: tianjian
 * @CreateDate: 2019/1/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private Logger logger = LoggerFactory.getLogger(MyUsernamePasswordAuthenticationFilter.class);

	public MyUsernamePasswordAuthenticationFilter() {
		super(new AntPathRequestMatcher("/oauth/token", "POST"));
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(getAuthenticationManager(), "authenticationManager must be specified");
		Assert.notNull(getSuccessHandler(), "AuthenticationSuccessHandler must be specified");
		Assert.notNull(getFailureHandler(), "AuthenticationFailureHandler must be specified");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
		String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
		String username = null, password = null;
		if(StringUtils.hasText(body)) {
		    JSONObject jsonObj = JSON.parseObject(body);
		    username = jsonObj.getString("username");
		    password = jsonObj.getString("password");
		}


		if (StringUtils.isEmpty(username)) {
			logger.warn("username is empty");
			username = "";
		}

		if (StringUtils.isEmpty(password)) {
			logger.warn("password is empty");
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
