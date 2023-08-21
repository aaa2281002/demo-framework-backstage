package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemType;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 类型业务接口类
 * @datetime 2019/10/11
 */
public interface SystemTypeService {

    /**
     * @param row 1 类型实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemType row);


    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemType
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemType selectByPrimaryKey(Long id);

    /**
     * @param row 1 类型实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共分页查询
     * @description 公共分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemType> findPageList(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 返回分页总数
     * @titel 公共分页查询总数
     * @description 公共分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemType> findByList(SystemType row);


    /**
     * @param code 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 根据父类代码查询集合
     * @description 根据父类代码查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemType> findByCodeList(String code);

    /**
     * @param row 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult edit(SystemType row);

    /**
     * @param idList   1 编号集合
     * @param parentId 2 上级编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult batchDeleteList(List<Long> idList, Long parentId);

    /**
     * @param id       1 编号
     * @param parentId 2 上级编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult delId(Long id, Long parentId);

    /**
     * @param id       1 编号
     * @param typeCode 2 类型代码
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String typeCode);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemType
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    SystemType getByIdParam(Long id);

    /**
     * @param id       1 编号
     * @param parentId 2 上级编号
     * @return com.framework.model.system.SystemType
     * @Titel 查询类型信息
     * @description 查询类型信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    SystemType getByIdParam(Long id, Long parentId);

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询类型列表
     * @description 本类后台管理根据条件分页查询类型列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageListParent(SystemType param);

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询类型列表
     * @description 本类后台管理根据条件分页查询类型列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemType param);
}
