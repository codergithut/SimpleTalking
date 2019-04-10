package com.user.security.config;


import com.user.security.filter.MyUsernamePasswordAuthenticationFilter;
import com.user.security.handler.HttpStatusLoginFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

/**
 * @ProjectName: com.security.config
 * @Description: post 方式请求登录验证 配置
 * @Author: tianjian
 * @CreateDate: 2019/1/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class JsonLoginConfigurer<T extends JsonLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

	private MyUsernamePasswordAuthenticationFilter authFilter;

	/**
	 * 过滤器配置
	 */
	public JsonLoginConfigurer() {
		this.authFilter = new MyUsernamePasswordAuthenticationFilter();
	}

	/**
	 * 过滤器组合
	 * @param http 安全过滤链
	 * @throws Exception
	 */
	@Override
	public void configure(B http) throws Exception {
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
		authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

		MyUsernamePasswordAuthenticationFilter filter = postProcess(authFilter);
		http.addFilterAfter(filter, LogoutFilter.class);
	}

	/**
	 * 登录成功
	 * @param authSuccessHandler
	 * @return
	 */
	public JsonLoginConfigurer<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
		authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
		return this;
	}

}
