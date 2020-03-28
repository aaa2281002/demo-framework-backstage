package com.framework.dao.mapper.system;

import com.framework.model.entity.system.SystemRoleMenuButton;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.system
 * @Description 角色菜单按钮关联mapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository("systemRoleMenuButtonMapper")
public interface SystemRoleMenuButtonMapper {

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号、菜单编号、按钮编号集合物理批量删除
     * @Description 公共根据角色编号、菜单编号、按钮编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:21
     */
    int deleteList(SystemRoleMenuButton record);

    /**
     * @param idList 1 角色编号集合
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号集合物理删除关联按钮
     * @Description 公共根据角色编号集合物理删除关联按钮
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:18
     */
    int deleteRoleList(List<Long> idList);

    /**
     * @param map 1 参数键值对集合
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色、菜单编号物理删除关联按钮
     * @Description 公共根据角色、菜单编号物理删除关联按钮
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:18
     */
    int deleteRoleMenuId(Map<String, Long> map);

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemRoleMenuButton record);

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insertSelective(SystemRoleMenuButton record);

    /**
     * @param list 1 角色菜单按钮关联类批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemRoleMenuButton> list);

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:15
     */
    List<SystemRoleMenuButton> findByList(SystemRoleMenuButton record);

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据菜单编号查询按钮代码集合
     * @Description 公共根据菜单编号查询按钮代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    List<SystemRoleMenuButton> findByMenuIdListButtonCode(Long menuId);

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据条件查询存在集合
     * @Description 公共根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:11
     */
    List<SystemRoleMenuButton> findByIsExistList(SystemRoleMenuButton record);

    /**
     * @param buttonId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据按钮编号查询菜单代码集合
     * @Description 公共根据按钮编号查询菜单代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    List<SystemRoleMenuButton> findByButtonIdListRoleCodeAndMenuCode(Long buttonId);

}