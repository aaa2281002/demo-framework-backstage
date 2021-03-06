package com.framework.dao.mapper.system;

import com.framework.model.entity.system.SystemRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.system
 * @Description 角色mapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository("systemRoleMapper")
public interface SystemRoleMapper {
    /**
     * @param id
     * @return 成功返回大于1，失败返回小于1
     * @Author 邋遢龘鵺
     * @description 根据编号，物理删除数据。
     */
    //int deleteByPrimaryKey(Long id);

    /**
     * @param record 1 角色实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insertSelective(SystemRole record);

    /**
     * @param list 1 角色批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemRole> list);

    /**
     * @param record 1 角色实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemRole record);

    /**
     * @param list 1 角色批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    int updateList(List<SystemRole> list);

    /**
     * @param paramMap 1  参数泛型键值对集合
     * @return com.framework.model.entity.system.SystemRole
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemRole selectByPrimaryKey(Map<String, Object> paramMap);

    /**
     * @param record 1 角色实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    int isExist(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRole>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemRole> findPageList(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRole>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemRole> findByList(SystemRole record);

}