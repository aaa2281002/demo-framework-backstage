package com.framework.common.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.model.properties
 * @Description 初始化令牌属性类
 * @Date 2022/3/2 13:47
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "custom.init.token")
public class InitTokenProperties {
    /**
     * 使用redis存储token
     */
    private Boolean redis = true;

    /**
     * 单设备登陆
     */
    private Boolean sdl = true;

    /**
     * 存储权限数据
     */
    private Boolean storePerms = true;

    /**
     * token默认过期时间
     */
    private Integer tokenExpireTime = 30;

    /**
     * 用户选择保存登录状态对应token过期时间（天）
     */
    private Integer saveLoginTime = 7;

    /**
     * 限制用户登录错误次数（次）
     */
    private Integer loginTimeLimit = 10;

    /**
     * 错误超过次数后多少分钟后才能继续登录（分钟）
     */
    private Integer loginAfterTime = 10;

    public Boolean getRedis() {
        return redis;
    }

    public void setRedis(Boolean redis) {
        this.redis = redis;
    }

    public Boolean getSdl() {
        return sdl;
    }

    public void setSdl(Boolean sdl) {
        this.sdl = sdl;
    }

    public Boolean getStorePerms() {
        return storePerms;
    }

    public void setStorePerms(Boolean storePerms) {
        this.storePerms = storePerms;
    }

    public Integer getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Integer tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public Integer getSaveLoginTime() {
        return saveLoginTime;
    }

    public void setSaveLoginTime(Integer saveLoginTime) {
        this.saveLoginTime = saveLoginTime;
    }

    public Integer getLoginTimeLimit() {
        return loginTimeLimit;
    }

    public void setLoginTimeLimit(Integer loginTimeLimit) {
        this.loginTimeLimit = loginTimeLimit;
    }

    public Integer getLoginAfterTime() {
        return loginAfterTime;
    }

    public void setLoginAfterTime(Integer loginAfterTime) {
        this.loginAfterTime = loginAfterTime;
    }
}
