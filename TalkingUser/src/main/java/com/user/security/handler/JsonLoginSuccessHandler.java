package com.user.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.user.security.service.JwtUserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @ProjectName: com.security.security.handler
 * @Description: 用户鉴权成功逻辑
 * @Author: tianjian
 * @CreateDate: 2019/1/25
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/1/25
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@Service
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JwtUserService jwtUserService;

	/**
	 * 用户鉴权成功逻辑处理
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		//为用户颁发token
		String token = jwtUserService.createJWT((UserDetails)authentication.getPrincipal());

		//构造返回消息数据
		Map<String, Object> authInfo = new HashMap<>();
		byte[] baseInfo = Base64.decodeBase64(token.split("\\.")[1]);
		JSONObject userInfo = JSONObject.parseObject(new String(baseInfo));
		authInfo.put("Authorization", token);
		authInfo.put("sub", userInfo.get("sub").toString());
		authInfo.put("exp", userInfo.get("exp").toString());
		authInfo.put("iat", userInfo.get("iat").toString());
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Authorization", token);
		try {
			write(response, authInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void write(HttpServletResponse response, Object o)throws Exception{
		PrintWriter out=response.getWriter();
		out.println(JSONObject.toJSONString(o));
		out.flush();
		out.close();
	}


	
}
