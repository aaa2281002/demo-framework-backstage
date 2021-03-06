package com.framework.dao.mapper.system;

import com.framework.model.entity.system.SystemBlackListIp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.system
 * @Description 系统前端操作黑名单IPmapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository
public interface SystemBlackListIpMapper {
    /**
     * @param id 1 编号
     * @return int 成功返回大于1，失败返回小于1
     * @Titel 公共根据编号，物理删除数据。
     * @Description 公共根据编号，物理删除数据。
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:08
     */
    int deleteByPrimaryKey(Long id);

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
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemBlackListIp record);

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 大于0成功，小于等于0失败
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

}