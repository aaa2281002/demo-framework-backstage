package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemRoleMenu;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 角色菜单关联业务接口类
 * @datetime 2019/10/11
 */
public interface SystemRoleMenuService {

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据角色编号和菜单编号集合物理批量删除
     * @description 公共根据角色编号和菜单编号集合物理批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:07
     */
    int deleteList(SystemRoleMenu row);

    /**
     * @param roleId 1 角色编号
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据角色编号物理删除关联数据
     * @description 公共根据角色编号物理删除关联数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:10
     */
    int deleteRoleId(Long roleId);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共添加
     * @description 公共添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insert(SystemRoleMenu row);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemRoleMenu row);

    /**
     * @param list 1 角色菜单关联批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    int insertList(List<SystemRoleMenu> list);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<java.lang.Long>
     * @titel 公共根据条件查询菜单编号集合
     * @description 公共根据条件查询菜单编号集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:19
     */
    List<Long> findByMenuIdList(SystemRoleMenu row);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据条件查询存在集合
     * @description 公共根据条件查询存在集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    List<SystemRoleMenu> findByIsExistList(SystemRoleMenu row);

    /**
     * @param roleId 1 角色编号
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据角色编号查询菜单代码集合
     * @description 公共根据角色编号查询菜单代码集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    List<SystemRoleMenu> findByRoleIdListMenuCode(Long roleId);

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据菜单编号查询角色代码集合
     * @description 公共根据菜单编号查询角色代码集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    List<SystemRoleMenu> findByMenuIdListRoleCode(Long menuId);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    List<SystemRoleMenu> findByList(SystemRoleMenu row);

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 初始化查询
     * @description 初始化查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    List<SystemRoleMenu> findByInitList(SystemRoleMenu row);

    /**
     * @param roleId     1 角色编号
     * @param menuIdList 2 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:23
     */
    ResponseResult save(Long roleId, List<Long> menuIdList);

    /**
     * @param param 1 角色菜单关联实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询数据集合
     * @description 根据条件查询数据集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:23
     */
    ResponseResult findByParamList(SystemRoleMenu param);

    /**
     * @param roleId  1 角色编号
     * @param id      2 菜单编号
     * @param pId     3 菜单上级编号
     * @param keyword 4 搜索条件
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询树形菜单集合
     * @description 根据条件查询树形菜单集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:25
     */
    ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword);

}
