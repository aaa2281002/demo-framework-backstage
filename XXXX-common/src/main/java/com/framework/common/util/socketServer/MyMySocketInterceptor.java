package com.framework.common.util.socketServer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.util.socketServer
 * @description socket拦截器
 * @date 2023/2/13 20:12
 */
@Configuration
public class MyMySocketInterceptor implements HandshakeInterceptor {
    /**
     * @param request    1
     * @param response   2
     * @param wsHandler  3
     * @param attributes 4
     * @return boolean
     * @title 握手前
     * @description 握手前
     * @author 龘鵺
     * @datetime 2023/2/13 20:13
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("beforeHandshake:握手开始");
        String hostName = request.getRemoteAddress().getHostName();
        String sessionId = hostName + String.valueOf((int) (Math.random() * 1000));
        if (StringUtils.isNotBlank(sessionId)) {
            // 放入属性域
            attributes.put("session_id", sessionId);
            System.out.println("用户 session_id " + sessionId + " 握手成功！");
            return true;
        }
        System.out.println("用户登录已失效");
        return false;
    }

    /**
     * @param request   1
     * @param response  2
     * @param wsHandler 3
     * @param exception 4
     * @return void
     * @title 握手后
     * @description 握手后
     * @author 龘鵺
     * @datetime 2023/2/13 20:14
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("afterHandshake:握手完成");
    }
}
