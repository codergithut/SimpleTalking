package com.session.manager.security.bean;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @ProjectName: com.security.security.bean
 * @Description: JWT 自定义安全验证实体
 * @Author: tianjian
 * @CreateDate: 2019/1/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 3981518947978158945L;
	
	private UserDetails principal;
	private String credentials;
	private DecodedJWT token;
	
	public JwtAuthenticationToken(DecodedJWT token) {
		super(Collections.emptyList());
		this.token = token;
	}
	
	public JwtAuthenticationToken(UserDetails principal, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.token = token;
	}
	
	@Override
	public void setDetails(Object details) {
		super.setDetails(details);
		this.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
	
	public DecodedJWT getToken() {
		return token;
	}
	
}
