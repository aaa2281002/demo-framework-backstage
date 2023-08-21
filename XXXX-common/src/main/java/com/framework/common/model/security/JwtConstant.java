package com.framework.common.model.security;

import com.framework.common.util.other.UUIDUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.model.security
 * @description 登录参数工具类
 * @datetime 2023/5/19 10:37
 */
@Configuration
@ConfigurationProperties(prefix = "custom.init.jwt")
public class JwtConstant {

    /**
     * 单设备登陆
     */
    private Boolean sdl = true;
    /**
     * token分割
     */
    private String tokenSplit = "Bearer ";

    /**
     * JWT签名加密key
     */
    private String jwtSignKey = UUIDUtil.getUUID();

    /**
     * token参数头
     */
    private String header = "accessToken";

    /**
     * 失效时间(分)
     */
    private Integer expire = 30;

    /**
     * 权限参数头
     */
    private String authorities = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    private String saveLogin = "saveLogin";


    public String getTokenSplit() {
        return tokenSplit;
    }

    public Boolean getSdl() {
        return sdl;
    }

    public void setSdl(Boolean sdl) {
        this.sdl = sdl;
    }

    public void setTokenSplit(String tokenSplit) {
        this.tokenSplit = tokenSplit == null ? null : tokenSplit.trim();
    }

    public String getJwtSignKey() {
        return jwtSignKey;
    }

    public void setJwtSignKey(String jwtSignKey) {
        this.jwtSignKey = jwtSignKey == null ? null : jwtSignKey.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    public String getSaveLogin() {
        return saveLogin;
    }

    public void setSaveLogin(String saveLogin) {
        this.saveLogin = saveLogin == null ? null : saveLogin.trim();
    }

}
