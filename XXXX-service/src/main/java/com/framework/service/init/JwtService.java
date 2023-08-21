package com.framework.service.init;

import io.jsonwebtoken.Claims;

import java.util.Date;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.init
 * @description jwt业务工具接口类
 * @datetime 2023/5/19 10:18
 */
public interface JwtService {
    /**
     * @param account 1 账户名
     * @return java.lang.String
     * @Title 生成token
     * @Description 生成token
     * @Author 龘鵺
     * @DateTime 2023/5/19 14:20
     */
    String createToken(String account);

    /**
     * @param token 1 token字符串
     * @return io.jsonwebtoken.Claims
     * @Title 获取token中用户信息
     * @Description 获取token中用户信息
     * @Author 龘鵺
     * @DateTime 2023/5/19 14:19
     */
    Claims getTokenClaim(String token);

    /**
     * @param expirationTime 1 当前时间
     * @return boolean
     * @Title 验证token是否过期失效
     * @Description 验证token是否过期失效
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:09
     */
    boolean isTokenExpired(Date expirationTime);

    /**
     * @param token 1 token字符串
     * @return java.util.Date
     * @Title 获取token失效时间
     * @Description 获取token失效时间
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:09
     */
    Date getExpirationDateFromToken(String token);

    /**
     * @param token 1 token字符串
     * @return java.lang.String
     * @Title 获取用户名从token中
     * @Description 获取用户名从token中
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:10
     */
    String getUsernameFromToken(String token);

    /**
     * @param token 1 token字符串
     * @return java.util.Date
     * @Title 获取jwt发布时间
     * @Description 获取jwt发布时间
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:10
     */
    Date getIssuedAtDateFromToken(String token);

}
