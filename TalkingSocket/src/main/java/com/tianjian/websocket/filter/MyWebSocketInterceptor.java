package com.tianjian.websocket.filter;

import com.common.util.JWTUtil;
import com.tianjian.kafka.config.CommonConfig;
import com.tianjian.redis.service.UserTopicInfo;
import com.tianjian.websocket.handle.WebSocketPushHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tianjian on 2019/3/4.
 */
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.key}")
    private String secret;

    @Autowired
    private UserTopicInfo userTopicInfo;

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private WebSocketPushHandler webSocketPushHandler;

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            //通过普通的登录方式写入userId 提供登录页面跳转实现
            String token = ((ServletServerHttpRequest) request).getServletRequest().getParameter("token");
            String userId = JWTUtil.verifyToken(token, secret);
            boolean register = userTopicInfo.saveUserTopicInfo(userId,commonConfig.getTopic());
            if(!register) {
                webSocketPushHandler.removeSocketByUserId(userId);
                logger.warn("user have register");
                System.out.println("===================================");
            }
            String sessionId = UUID.randomUUID().toString();
            attributes.put("sessionId", sessionId);
            attributes.put("userId", userId);
        }

        return true;
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {


    }

    protected String getJwtToken(HttpServletRequest request) {
        String authInfo = request.getHeader("Authorization");
        return StringUtils.removeStart(authInfo, "Bearer ");
    }

}

