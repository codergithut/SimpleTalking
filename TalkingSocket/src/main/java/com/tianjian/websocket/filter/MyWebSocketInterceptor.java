package com.tianjian.websocket.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by tianjian on 2019/3/4.
 */
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        logger.info("xxx用户建立连接。。。");
        if (request instanceof ServletServerHttpRequest) {
            //通过普通的登录方式写入userId 提供登录页面跳转实现
            String userId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userId");
            attributes.put("userId", userId);
//            attributes.put("userId", "tianjian");
            logger.info("用户唯一标识:" + userId);
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

}

