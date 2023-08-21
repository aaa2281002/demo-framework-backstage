package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemDict;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 字典业务接口类
 * @datetime 2019/10/11
 */
public interface SystemDictService {
    /**
     * @param row 1 字典实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemDict row);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemDict
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemDict selectByPrimaryKey(Long id);

    /**
     * @param row 1 字典实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemDict> findPageList(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemDict> findByList(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 根据条件查询初始化集合
     * @description 根据条件查询初始化集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemDict> findByInitList(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(SystemDict row);

    /**
     * @param row 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult edit(SystemDict row);

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
     * @param id      1 编号
     * @param dictKey 2 字典键
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String dictKey);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemDict
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    SystemDict getByIdParam(Long id);

    /**
     * @param param 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询字典列表
     * @description 本类后台管理根据条件分页查询字典列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemDict param);

    /**
     * @param dictKey 1
     * @return com.framework.model.system.SystemDict
     * @titel 根据字典键查询字典信息
     * @description 根据字典键查询字典信息
     * @author 邋遢龘鵺
     * @datetime 2020/4/13 12:29
     */
    SystemDict getDictKey(String dictKey);

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Title 根据编号查询信息
     * @Description 根据编号查询信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    ResponseResult getByIdInfo(Long id);
}
