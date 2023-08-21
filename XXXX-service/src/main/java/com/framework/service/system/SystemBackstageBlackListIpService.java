package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemBackstageBlackListIp;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 系统后台黑名单IP业务接口类
 * @date 2021/12/12 17:35
 */
public interface SystemBackstageBlackListIpService {
    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemBackstageBlackListIp row);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemBackstageBlackListIp
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemBackstageBlackListIp selectByPrimaryKey(Long id);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemBackstageBlackListIp>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemBackstageBlackListIp> findPageList(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemBackstageBlackListIp>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemBackstageBlackListIp> findByList(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult edit(SystemBackstageBlackListIp row);

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
     * @param id 1 编号
     * @param ip 2 IP
     * @return com.framework.common.response.ResponseResult
     * @titel 本类私有查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String ip);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemBackstageBlackListIp
     * @titel 本类根据ID查询数据信息
     * @description 本类根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    SystemBackstageBlackListIp getByIdParam(Long id);

    /**
     * @param param 1 系统后台黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询系统后台黑名单IP列表
     * @description 本类后台管理根据条件分页查询系统后台黑名单IP列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemBackstageBlackListIp param);

    /**
     * @param ip 1 IP
     * @return com.framework.model.system.SystemBackstageBlackListIp
     * @titel 根据IP查询是否存在
     * @description 根据IP查询是否存在
     * @author 邋遢龘鵺
     * @datetime 2020/1/14 9:47
     */
    SystemBackstageBlackListIp getIp(String ip);

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
