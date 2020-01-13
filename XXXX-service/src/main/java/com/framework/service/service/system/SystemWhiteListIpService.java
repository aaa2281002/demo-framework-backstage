package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemWhiteListIp;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 系统后台操作系统后台操作白名单IP业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemWhiteListIpService {

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insert(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insertSelective(SystemWhiteListIp record);


    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemWhiteListIp record);


    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemWhiteListIp
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemWhiteListIp selectByPrimaryKey(Long id);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    int isExist(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemWhiteListIp>
     * @Titel 公共分页查询
     * @Description 公共分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemWhiteListIp> findPageList(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return int 返回分页总数
     * @Titel 公共分页查询总数
     * @Description 公共分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemWhiteListIp>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemWhiteListIp> findByList(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(SystemWhiteListIp record);

    /**
     * @param record 1 系统后台操作白名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult edit(SystemWhiteListIp record);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    public ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id 1 编号
     * @param ip 2 IP
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类私有查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String ip);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemWhiteListIp
     * @Titel 本类根据ID查询数据信息
     * @Description 本类根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    SystemWhiteListIp getByIdParam(Long id);

    /**
     * @param param 1 系统后台操作白名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询系统后台操作白名单IP列表
     * @Description 本类后台管理根据条件分页查询系统后台操作白名单IP列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemWhiteListIp param);
}
