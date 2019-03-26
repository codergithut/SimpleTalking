package com.session.manager.security.config;

import com.session.manager.security.filter.OptionsRequestFilter;
import com.session.manager.security.handler.JsonLoginSuccessHandler;
import com.session.manager.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @ProjectName: com.security.config
 * @Description: wen安全配置类
 * @Author: tianjian
 * @CreateDate: 2019/1/29
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/29
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserService jwtUserService;


	@Autowired
	JsonLoginSuccessHandler jsonLoginSuccessHandler;

	@Autowired
	Md5PasswordEncoder md5PasswordEncoder;



	/**
	 * 配置security安全服务权限和用户及系统安全选项
	 * @param http
	 * @throws Exception
	 */
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/oauth/token","/actuator/health", "/webSocketServer", "/hello").permitAll()
				.antMatchers(
						"/**"
				).hasAnyRole("USER")
		        .anyRequest().authenticated()
		        .and()
		    .csrf().disable()
		    .formLogin().disable()
		    .sessionManagement().disable()
		    .cors()
		    .and()
				.addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
		    .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
		    		new Header("Authorization","*"),
		    		new Header("Access-Control-Expose-Headers","Authorization"))))
		    .and()
		    .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(jsonLoginSuccessHandler)
		    .and()
		    .logout()
//		        .logoutUrl("/logout")   //默认就是"/logout"
		        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
		    .and()
		    .sessionManagement().disable();
	}

	/**
	 * 配置安全验证逻辑,包含了用户名密码验证和验证加密规则
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(md5PasswordEncoder);
		daoAuthenticationProvider.setUserDetailsService(jwtUserService);
		auth.authenticationProvider(daoAuthenticationProvider);
	}

	/**
	 * 安全管理器
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}


	/**
	 * 配置跨域等基础头信息
	 * @return
	 */
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
