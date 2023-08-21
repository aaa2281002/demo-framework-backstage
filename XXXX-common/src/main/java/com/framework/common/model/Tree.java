package com.framework.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.lottery.common.model
 * @description 树形公共类
 * @datetime 2019/10/11
 */
public class Tree implements Serializable {
    private static final long serialVersionUID = -1L;
    //编号
    private String id;
    //节点字段名,一般对应表字段名
    private String field;
    //节点名称
    private String title;
    //是否为生产实体
    private int isEntity;
    //设置个性css
    private String iconSkin;
    //点击节点弹出新窗口对应的 url。需开启 isJump 参数
    private String href;
    //节点是否为禁用状态,默认 false
    private Boolean disabled = false;
    //展开 true / 折叠 false
    private Boolean spread = false;
    //是否选中
    private Boolean checked = false;
    //存放任意对象
    private Object obj;
    //子节点集合
    private List<Tree> children = new ArrayList<Tree>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public int getIsEntity() {
        return isEntity;
    }

    public void setIsEntity(int isEntity) {
        this.isEntity = isEntity;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin == null ? null : iconSkin.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
