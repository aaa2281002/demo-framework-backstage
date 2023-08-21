package com.framework.mapper.system;

import com.framework.model.system.SystemMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.mapper.system
 * @description 菜单mapper接口类
 * @datetime 2019/10/11
 */
@Repository("systemMenuMapper")
public interface SystemMenuMapper {

    /**
     * @param row 1 菜单实体类对象
     * @return int 大于等于1成功， 0失败
     * @title 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int delete(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return int 大于等于1成功， 0失败
     * @title 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return int 大于0成功，小于等于0失败
     * @title 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemMenu row);

    /**
     * @param list 1 菜单批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    int insertList(List<SystemMenu> list);

    /**
     * @param row 1 菜单实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemMenu row);

    /**
     * @param list 1 菜单批量修改集合
     * @return int 返回成功数量
     * @titel 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    int updateList(List<SystemMenu> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemMenu
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemMenu selectByPrimaryKey(Long id);

    /**
     * @param row 1 菜单实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExistUrl(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemMenu> findPageList(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemMenu> findByList(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @titel 根据条件查询初始化集合
     * @description 根据条件查询初始化集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemMenu> findByInitList(SystemMenu row);

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @titel 公共根据条件查询树形集合
     * @description 公共根据条件查询树形集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemMenu> findByTreeList(SystemMenu row);

}