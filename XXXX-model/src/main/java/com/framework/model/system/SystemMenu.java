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
import java.util.List;
import java.util.Objects;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 菜单实体类
 * @datetime 2019/10/11
 */
@ApiModel(value = "菜单和按钮", description = "菜单和按钮", parent = BaseModel.class)
public class SystemMenu extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用:1=启用,2=禁用
    @ApiModelProperty(value = "否启用=1启用,2禁用", name = "isEnable", dataType = "Integer", required = true, allowableValues = "1", hidden = false)
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.IS_ENABLE")
    private Integer isEnable;
    //菜单上级编号
    @ApiModelProperty(value = "菜单上级编号", name = "parentId", dataType = "Long", required = true, allowableValues = "20", hidden = false)
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误上级编号", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.PARENT_ID")
    private Long parentId;
    //菜单名称
    @ApiModelProperty(value = "名称", name = "name", dataType = "String", required = true, allowableValues = "64", hidden = false)
    @NotBlank(message = "请输入菜单名称", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Size(message = "菜单名称最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.NAME")
    private String name;
    //菜单唯一代码
    @ApiModelProperty(value = "唯一代码", name = "code", dataType = "String", required = true, allowableValues = "64", hidden = false)
    @NotBlank(message = "请输入菜单代码", groups = {ValidationGroup.formAdd.class})
    @Size(message = "菜单代码最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formAdd.class})
    @QueryParam(code = "p.CODE")
    private String code;
    //菜单层级,顶级菜单为0因为只有一个，模块菜单为1有多个(如：系统管理，XXX管理),每级菜单根据上级菜单来自增1设置菜单级别
    @ApiModelProperty(value = "菜单层级", name = "level", dataType = "Integer", required = false, allowableValues = "11", hidden = true)
    @QueryParam(code = "p.LEVEL")
    private Integer level;
    //请求URL
    @ApiModelProperty(value = "请求URL", name = "url", dataType = "String", required = false, allowableValues = "512", hidden = false)
    @Size(message = "请求地址最多512个字符", max = NumeralUtil.POSITIVE_FIVE_HUNDRED_AND_TWELVE, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.URL")
    private String url;
    //图标
    @ApiModelProperty(value = "图标", name = "icon", dataType = "String", required = true, allowableValues = "128", hidden = false)
    @Size(message = "图标最多128个字符", max = NumeralUtil.POSITIVE_ONE_HUNDRED_AND_TWENTY_EIGHT, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ICON")
    private String icon;
    //排序
    @ApiModelProperty(value = "排序", name = "indexSort", dataType = "Integer", required = false, allowableValues = "11", hidden = false)
    @QueryParam(code = "p.INDEX_SORT")
    private Integer indexSort;
    //类别，9999=菜单 1=按钮
    @ApiModelProperty(value = "类别，9999=菜单 1=按钮", name = "category", dataType = "Integer", required = true, allowableValues = "4", hidden = false)
    @NotNull(message = "请选择类别", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误菜单类型", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE, message = "错误菜单类型", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.CATEGORY")
    private Integer category;
    //菜单类型，9999=管理员菜单 1=普通菜单
    @ApiModelProperty(value = "菜单类型，9999=管理员菜单 1=普通菜单", name = "type", dataType = "Integer", required = true, allowableValues = "4", hidden = false)
    @NotNull(message = "请选择类型", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误菜单类型", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE, message = "错误菜单类型", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.TYPE")
    private Integer type;
    //打开方式=目标：_blank=浏览器打开一个新的页面，_self=在我的首页后面打开一个新的窗口， _item=子项菜单, _event=提交事件
    @ApiModelProperty(value = "打开方式：_blank=浏览器打开一个新的页面，_self=在我的首页后面打开一个新的窗口， _item=子项菜单, _event=提交事件", name = "target", dataType = "String", required = true, allowableValues = "64", hidden = false)
    @NotBlank(message = "请选择打开方式", groups = {ValidationGroup.formEdit.class})
    @Size(message = "打开方式最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class})
    @QueryParam(code = "p.TARGET")
    private String target;

    //以下临时字段
    //上级菜单名称
    @ApiModelProperty(value = "上级菜单名称", name = "parentName", dataType = "String", required = false, allowableValues = "64", hidden = false)
    @QueryParam(code = "sm.NAME")
    private String parentName;
    //上级菜单ID集合
    @ApiModelProperty(value = "上级菜单ID集合", name = "parentIds", dataType = "List<Long>", required = false, hidden = true)
    private List<Long> parentIds;
    //子菜单集合
    @ApiModelProperty(value = "子菜单集合", name = "childMenuList", dataType = "List", required = false, hidden = true)
    private List<SystemMenu> childMenuList;
    //按钮代码集合
    @ApiModelProperty(value = "按钮代码集合", name = "buttonCodeList", dataType = "List", required = false, hidden = true)
    private List<String> buttonCodeList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getIndexSort() {
        return indexSort;
    }

    public void setIndexSort(Integer indexSort) {
        this.indexSort = indexSort;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public List<Long> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<Long> parentIds) {
        this.parentIds = parentIds;
    }

    public List<SystemMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<SystemMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public List<String> getButtonCodeList() {
        return buttonCodeList;
    }

    public void setButtonCodeList(List<String> buttonCodeList) {
        this.buttonCodeList = buttonCodeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemMenu that = (SystemMenu) o;
        return Objects.equals(isEnable, that.isEnable) && Objects.equals(parentId, that.parentId) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(level, that.level) && Objects.equals(url, that.url) && Objects.equals(icon, that.icon) && Objects.equals(indexSort, that.indexSort) && Objects.equals(category, that.category) && Objects.equals(type, that.type) && Objects.equals(target, that.target) && Objects.equals(parentName, that.parentName) && Objects.equals(parentIds, that.parentIds) && Objects.equals(childMenuList, that.childMenuList) && Objects.equals(buttonCodeList, that.buttonCodeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnable, parentId, name, code, level, url, icon, indexSort, category, type, target, parentName, parentIds, childMenuList, buttonCodeList);
    }
}