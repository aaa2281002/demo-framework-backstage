package com.framework.model.system.vo;

import com.framework.model.system.SystemRoleMenu;
import com.framework.model.system.SystemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.model.system.vo
 * @description 系统类型视图类
 * @datetime 2023/3/30 17:45
 */
@ApiModel(value = "系统类型视图", description = "系统类型视图", parent = SystemType.class)
public class SystemTypeVo extends SystemType implements Serializable {
    //上级类型代码
    @ApiModelProperty(value = "上级类型代码", name = "parentTypeCode", dataType = "String", required = false, allowableValues = "64", hidden = false)
    private String parentTypeCode;

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode == null ? null : parentTypeCode.trim();
    }
}
