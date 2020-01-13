package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 字典实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemDict extends BaseModel implements Serializable {
    private static final long serialVersionUID = 205486277131358011L;
    //字典键，唯一
    private String dictKey;
    //是否启用1启用,2禁用
    private Integer isEnable;
    //描述
    private String description;

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}