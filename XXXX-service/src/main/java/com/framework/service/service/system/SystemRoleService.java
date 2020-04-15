package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemRole;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 角色业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemRoleService {

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
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
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
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemRole
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemRole selectByPrimaryKey(Long id);

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

    /**
     * @param record 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(SystemRole record);

    /**
     * @param record 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult edit(SystemRole record);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id       1 编号
     * @param roleCode 2 角色代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String roleCode);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemRole
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    SystemRole getByIdParam(Long id);

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询角色列表
     * @Description 本类后台管理根据条件分页查询角色列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemRole param);

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询角色列表
     * @Description 根据条件查询角色列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:52
     */
    ResponseResult findParamList(SystemRole param);

}
