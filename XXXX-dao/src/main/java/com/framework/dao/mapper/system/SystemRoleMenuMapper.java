package com.framework.dao.mapper.system;

import com.framework.model.entity.system.SystemRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.system
 * @Description 角色菜单关联mapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository("systemRoleMenuMapper")
public interface SystemRoleMenuMapper {

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号和菜单编号集合物理批量删除
     * @Description 公共根据角色编号和菜单编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:07
     */
    int deleteList(SystemRoleMenu record);

    /**
     * @param roleId 1 角色编号
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号物理删除关联数据
     * @Description 公共根据角色编号物理删除关联数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:10
     */
    int deleteRoleId(Long roleId);

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemRoleMenu record);

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insertSelective(SystemRoleMenu record);

    /**
     * @param list 1 角色菜单关联批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemRoleMenu> list);

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<java.lang.Long>
     * @Titel 公共根据条件查询菜单编号集合
     * @Description 公共根据条件查询菜单编号集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:19
     */
    List<Long> findByMenuIdList(SystemRoleMenu record);

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据条件查询存在集合
     * @Description 公共根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:20
     */
    List<SystemRoleMenu> findByIsExistList(SystemRoleMenu record);

    /**
     * @param roleId 1 角色编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据角色编号查询菜单代码集合
     * @Description 公共根据角色编号查询菜单代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    List<SystemRoleMenu> findByRoleIdListMenuCode(Long roleId);

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据菜单编号查询角色代码集合
     * @Description 公共根据菜单编号查询角色代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    List<SystemRoleMenu> findByMenuIdListRoleCode(Long menuId);

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:20
     */
    List<SystemRoleMenu> findByList(SystemRoleMenu record);

}