package com.framework.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.model
 * @Description 验证码图片实体类参数类
 * @DateTime 2019/12/21 11:13
 * @Version 1.0
 */
public class CaptchaImage implements Serializable {
    private static final long serialVersionUID = -6233883220979658384L;
    /**
     * 随机数
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public CaptchaImage(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    //验证码代码，验证码过期时间（单位：秒）
    public CaptchaImage(String code, int expireIn) {
        this.code = code;
        //当前时间加上设置过期的时间
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        //如果过期时间在当前日期之前，则验证码过期
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
