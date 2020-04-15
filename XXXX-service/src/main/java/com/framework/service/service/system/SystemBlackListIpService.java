package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemBlackListIp;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 系统前端操作黑名单IP业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemBlackListIpService {

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insertSelective(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemBlackListIp record);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemBlackListIp selectByPrimaryKey(Long id);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    int isExist(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemBlackListIp> findPageList(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemBlackListIp> findByList(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult edit(SystemBlackListIp record);

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
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 本类根据ID查询数据信息
     * @Description 本类根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    SystemBlackListIp getByIdParam(Long id);

    /**
     * @param param 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询系统前端操作黑名单IP列表
     * @Description 本类后台管理根据条件分页查询系统前端操作黑名单IP列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemBlackListIp param);

    /**
     * @param ip 1 IP
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 根据IP查询是否存在
     * @Description 根据IP查询是否存在
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/14 9:47
     */
    public SystemBlackListIp getIp(String ip);
}
