package com.framework.web.config.initWebSocketConfig;

import com.framework.common.util.socketServer.HttpAuthHandler;
import com.framework.common.util.socketServer.MyMySocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.web.config.initWebSocketConfig
 * @description socket配置类
 * @date 2023/2/13 19:19
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Autowired
    private HttpAuthHandler httpAuthHandler;
    @Autowired
    private MyMySocketInterceptor myMySocketInterceptor;
//
//    @Bean
//    public HttpFirewall httpFirewall() {
//        return new DefaultHttpFirewall();
//    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(httpAuthHandler, "myws")
                .addInterceptors(myMySocketInterceptor)
                .setAllowedOrigins("*")
//                .setAllowedOriginPatterns("*")
        ;

    }
//    @Bean
//    public ServerEndpointExporter serverEndpoint() {
//        return new ServerEndpointExporter();
//    }
}
