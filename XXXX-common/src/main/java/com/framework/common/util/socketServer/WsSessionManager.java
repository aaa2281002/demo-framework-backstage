package com.framework.common.util.socketServer;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.util.socketServer
 * @description 连接保存工具类
 * @date 2023/2/13 20:00
 */
public class WsSessionManager {

    /**
     * 保存连接 session 的地方
     */
    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * @param key     1 键值
     * @param session 2 session对象
     * @titel 添加 session
     * @description 添加 session
     * @author 龘鵺
     * @datetime 2023/2/13 20:06
     */
    public static void add(String key, WebSocketSession session) {
        // 添加 session
        SESSION_POOL.put(key, session);
    }

    /**
     * @param key 1 键值
     * @return org.springframework.web.socket.WebSocketSession
     * @titel 删除 session,会返回删除的 session
     * @description 删除 session,会返回删除的 session
     * @author 龘鵺
     * @datetime 2023/2/13 20:01
     */
    public static WebSocketSession remove(String key) {
        // 删除 session
        return SESSION_POOL.remove(key);
    }

    /**
     * @param key 1 键值
     * @titel 删除并同步关闭连接
     * @description 删除并同步关闭连接
     * @author 龘鵺
     * @datetime 2023/2/13 20:00
     */
    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                // 关闭连接
                session.close();
            } catch (IOException e) {
                // todo: 关闭出现异常处理
                e.printStackTrace();
            }
        }
    }

    /**
     * @param key 1 键值
     * @return org.springframework.web.socket.WebSocketSession
     * @titel 获得 session
     * @description 获得 session
     * @author 龘鵺
     * @datetime 2023/2/13 20:00
     */
    public static WebSocketSession get(String key) {
        // 获得 session
        return SESSION_POOL.get(key);
    }
}
