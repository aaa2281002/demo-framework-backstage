package com.framework.model.system.vo;

import com.framework.model.system.SystemRoleMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 龘鵺
 * @ClassName com.framework.model.system.vo
 * @Description 角色菜单视图类
 * @Date 24/5/2023 上午10:32
 * @Version 1.0
 */
@ApiModel(value = "角色菜单视图", description = "角色菜单视图", parent = SystemRoleMenu.class)
public class SystemRoleMenuVo extends SystemRoleMenu implements Serializable {
    //菜单编号
    @ApiModelProperty(value = "菜单编号", name = "menuIdList", dataType = "List<Long>", required = false, hidden = true)
    private List<Long> menuIdList;

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
