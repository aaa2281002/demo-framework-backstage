package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 按钮实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemButton extends BaseModel implements Serializable {
    private static final long serialVersionUID = -9031887483497424932L;
    //是否启用:1=启用,2=禁用
    private Integer isEnable;
    //按钮唯一代码
    private String buttonCode;
    //按钮名称
    private String buttonName;
    //角色描述
    private String description;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode == null ? null : buttonCode.trim();
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}