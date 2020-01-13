package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 菜单实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemMenu extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1884794922265969857L;
    //是否启用:1=启用,2=禁用
    private Integer isEnable;
    //本表上级编号
    private Long parentId;
    //菜单名称
    private String menuName;
    //菜单唯一代码
    private String menuCode;
    //菜单层级,顶级菜单为0因为只有一个，模块菜单为1有多个(如：系统管理，XXX管理),每级菜单根据上级菜单来自增1设置菜单级别
    private Integer menuLevel;
    //请求URL
    private String urlPath;
    //图标
    private String icon;
    //排序
    private Integer indexSort;
    //菜单类型，1管理员菜单 2普通菜单
    private Integer adminType;
    //打开方式=目标：_blank=浏览器打开一个新的页面，_self=在我的首页后面打开一个新的窗口
    private String target;
    //以下临时字段
    //上级菜单名称
    private String parentName;
    //上级菜单ID集合
    private List<Long> parentIds;
    //子菜单集合
    private List<SystemMenu> childMenuList;
    //按钮代码集合
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
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

    public Integer getAdminType() {
        return adminType;
    }

    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
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
    public String toString() {
        return "SystemMenu{" +
                "isEnable=" + isEnable +
                ", parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", menuLevel=" + menuLevel +
                ", urlPath='" + urlPath + '\'' +
                ", icon='" + icon + '\'' +
                ", indexSort=" + indexSort +
                ", adminType=" + adminType +
                ", target=" + target +
                ", parentName='" + parentName + '\'' +
                ", parentIds=" + parentIds +
                '}';
    }
}