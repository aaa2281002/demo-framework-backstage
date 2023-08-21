package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemUser;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 用户业务接口类
 * @datetime 2019/10/11
 */
public interface SystemUserService {

    /**
     * @param row 1 用户实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemUser row);

    /**
     * @param list 1 用户批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    int insertList(List<SystemUser> list);

    /**
     * @param row 1 用户实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemUser row);

    /**
     * @param list 1 用户批量修改集合
     * @return int 返回成功数量
     * @titel 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    int updateList(List<SystemUser> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemUser selectByPrimaryKey(Long id);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemUser selectByParam(Long id);

    /**
     * @param row 1 用户实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemUser row);

    /**
     * @param loginName 1 登录账号
     * @return com.framework.model.system.SystemUser
     * @titel 根据登录账户名称查询用户数据
     * @description 根据登录账户名称查询用户数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 20:30
     */
    SystemUser queryForLoginName(String loginName);

    /**
     * @param row 1 用户实体类对象
     * @return java.util.List<com.framework.model.system.SystemUser>
     * @titel 公共分页查询
     * @description 公共分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemUser> findPageList(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return int 返回分页总数
     * @titel 公共分页查询总数
     * @description 公共分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return java.util.List<com.framework.model.system.SystemUser>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemUser> findByList(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult edit(SystemUser row);

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改密码
     * @description 修改密码
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult password(SystemUser row);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult delId(Long id);

    /**
     * @param id        1 编号
     * @param loginName 2 账户名
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String loginName);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    SystemUser getByIdParam(Long id);

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询用户列表
     * @description 本类后台管理根据条件分页查询用户列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemUser param);

    /**
     * @return boolean
     * @title 验证用户是否为超级管理员权限
     * @description 验证用户是否为超级管理员权限
     * @author 龘鵺
     * @dateTime 2023/6/2 11:23
     */
    boolean isAuth();
}
