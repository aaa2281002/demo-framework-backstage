package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 类型实体类
 * @datetime 2019/10/11
 */
@ApiModel(value = "类型", description = "类型", parent = BaseModel.class)
public class SystemType extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用1启用,2禁用
    @ApiModelProperty(value = "否启用=1启用,2禁用", name = "isEnable", dataType = "Integer", required = true, allowableValues = "1", hidden = false)
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @QueryParam(name = "isEnable", code = "p.IS_ENABLE")
    private Integer isEnable;
    //上级类型
    @ApiModelProperty(value = "上级类型", name = "parentId", dataType = "Long", required = true, allowableValues = "20", hidden = false)
    @NotNull(message = "请选择上级类型", groups = {ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class, ValidationGroup.formQueryItem.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class, ValidationGroup.formQueryItem.class})
    @Max(value = Long.MAX_VALUE, message = "错误选择", groups = {ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class,
            ValidationGroup.formQueryItem.class})
    @QueryParam(name = "parentId", code = "p.PARENT_ID")
    private Long parentId;
    //类型名称
    @ApiModelProperty(value = "类型名称", name = "typeName", dataType = "String", required = true, allowableValues = "64", hidden = false)
    @NotBlank(message = "请输入类型名称", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @Size(message = "类型名称最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @QueryParam(code = "p.TYPE_NAME")
    private String typeName;
    //类型代码
    @ApiModelProperty(value = "类型代码", name = "typeCode", dataType = "String", required = true, allowableValues = "64", hidden = false)
    @NotBlank(message = "请输入类型代码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @Size(message = "类型代码最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @QueryParam(code = "p.TYPE_CODE")
    private String typeCode;
    //类型数值
    @ApiModelProperty(value = "类型数值", name = "typeValue", dataType = "Integer", required = true, allowableValues = "11", hidden = false)
    @NotNull(message = "请输入类型数值", groups = {ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @Min(message = "类型数值错误", value = NumeralUtil.POSITIVE_ONE, groups = {ValidationGroup.formAddItem.class,
            ValidationGroup.formEditItem.class})
    @Max(message = "类型数值错误", value = Integer.MAX_VALUE, groups = {ValidationGroup.formAddItem.class,
            ValidationGroup.formEditItem.class})
    @QueryParam(code = "p.TYPE_VALUE")
    private Integer typeValue;
    //描述
    @ApiModelProperty(value = "描述", name = "description", dataType = "String", required = false, allowableValues = "2000", hidden = false)
    @Size(message = "描述最多2000个字符", max = NumeralUtil.POSITIVE_ONE_TWO_THOUSAND, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class
            , ValidationGroup.formAddItem.class, ValidationGroup.formEditItem.class})
    @QueryParam(code = "p.DESCRIPTION")
    private String description;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public Integer getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Integer typeValue) {
        this.typeValue = typeValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}