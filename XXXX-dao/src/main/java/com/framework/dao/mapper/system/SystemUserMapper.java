package com.framework.dao.mapper.system;

import com.framework.model.entity.system.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.system
 * @Description 用户mapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository("systemUserMapper")
public interface SystemUserMapper {
    /**
     * @param id 1 编号
     * @return int 成功返回大于1，失败返回小于1
     * @Titel 公共根据编号，物理删除数据。
     * @Description 公共根据编号，物理删除数据。
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:08
     */
    //int deleteByPrimaryKey(Long id);

    /**
     * @param record 1 用户实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(SystemUser record);

    /**
     * @param record 1 用户实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insert(SystemUser record);

    /**
     * @param record 1 用户实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insertSelective(SystemUser record);

    /**
     * @param list 1 用户批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemUser> list);


    /**
     * @param record 1 用户实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemUser record);

    /**
     * @param record 1 用户实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemUser record);

    /**
     * @param list 1 用户批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    int updateList(List<SystemUser> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemUser selectByPrimaryKey(Long id);

    /**
     * @param record 1 用户实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    int isExist(SystemUser record);

    /**
     * @param loginName 1 登录账号
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 根据登录账户名称查询用户数据
     * @Description 根据登录账户名称查询用户数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 20:30
     */
    SystemUser queryForLoginName(String loginName);

    /**
     * @param record 1 用户实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUser>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemUser> findPageList(SystemUser record);

    /**
     * @param record 1 用户实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemUser record);

    /**
     * @param record 1 用户实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUser>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemUser> findByList(SystemUser record);

}