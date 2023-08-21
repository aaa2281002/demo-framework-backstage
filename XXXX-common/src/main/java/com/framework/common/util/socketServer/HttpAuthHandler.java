package com.framework.common.util.socketServer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.util.socketServer
 * @description Http身份验证处理程序
 * @date 2023/2/13 19:55
 */
@Configuration
public class HttpAuthHandler extends TextWebSocketHandler {

    //处理二进制消息
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        System.out.println("handleBinaryMessage:8");
        super.handleBinaryMessage(session, message);
    }

    /**
     * @param session 1
     * @titel 建立成功事件
     * @description 建立成功事件
     * @author 龘鵺
     * @datetime 2023/2/13 19:59
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("afterConnectionEstablished:2");
        Object sessionId = session.getAttributes().get("session_id");
        if (sessionId != null) {
            // 用户连接成功，放入在线用户缓存
            WsSessionManager.add(sessionId.toString(), session);
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
//        super.afterConnectionEstablished(session);
    }

    //句柄消息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("handleMessage:3");
        super.handleMessage(session, message);
    }

    /**
     * @param session 1
     * @param message 2
     * @throws Exception
     * @titel 接收消息事件
     * @description 接收消息事件
     * @author 龘鵺
     * @datetime 2023/2/13 20:10
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextMessage:4");
        // 获得客户端传来的消息
        String payload = message.getPayload();
        Object sessionId = session.getAttributes().get("session_id");
        System.out.println("server 接收到 " + sessionId + " 发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给 " + sessionId + " 消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    //处理 ping 值消息
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        System.out.println("handlePongMessage:6");
        super.handlePongMessage(session, message);
    }

    //处理传输错误
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("handleTransportError:7");
        super.handleTransportError(session, exception);
    }

    /**
     * @param session 1
     * @param status  2
     * @throws Exception
     * @titel socket 断开连接时
     * @description socket 断开连接时
     * @author 龘鵺
     * @datetime 2023/2/13 20:10
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("afterConnectionClosed:5");
        Object sessionId = session.getAttributes().get("session_id");
        if (sessionId != null) {
            // 用户退出，移除缓存
            WsSessionManager.remove(sessionId.toString());
        }
    }

    //支持部分消息
    @Override
    public boolean supportsPartialMessages() {
        System.out.println("supportsPartialMessages:1");
        return super.supportsPartialMessages();
    }
}
