package com.session.manager.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: com.security.security.handler
 * @Description: 用户鉴权失败
 * @Author: tianjian
 * @CreateDate: 2019/1/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {

	/**
	 * 验证失败返回验证失败返回码
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setHeader("Content-Type", "application/json");
	}

	
}
