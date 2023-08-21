package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 字典实体类
 * @datetime 2019/10/11
 */
@ApiModel(value = "字典", description = "字典", parent = BaseModel.class)
public class SystemDict extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用1启用,2禁用
    @ApiModelProperty(value = "否启用=1启用,2禁用", name = "isEnable", dataType = "Integer", required = true, allowableValues = "1", hidden = false)
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.IS_ENABLE")
    private Integer isEnable;
    //字典键，唯一
    @ApiModelProperty(value = "字典键", name = "dictKey", dataType = "String", required = true, allowableValues = "255", hidden = false)
    @NotBlank(message = "请输入字典名称", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Size(message = "字典名称最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.DICT_KEY")
    private String dictKey;
    //描述
    @ApiModelProperty(value = "描述", name = "description", dataType = "String", required = false, allowableValues = "1000", hidden = false)
    @Size(message = "描述最多1000个字符", max = NumeralUtil.POSITIVE_ONE_THOUSAND, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.DESCRIPTION")
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