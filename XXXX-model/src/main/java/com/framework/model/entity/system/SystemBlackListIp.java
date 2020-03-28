package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 系统前端操作黑名单IP实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemBlackListIp extends BaseModel implements Serializable {
    private static final long serialVersionUID = 2968779752243055898L;
    //是否启用=1启用,2禁用
    private Integer isEnable;
    //IP
    private String ip;
    //描述
    private String description;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}