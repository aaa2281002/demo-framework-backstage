package com.framework.mapper.system;

import com.framework.model.system.SystemUser;
import com.framework.model.system.vo.SystemUserVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.mapper.system
 * @description 用户mapper接口类
 * @datetime 2019/10/11
 */
@Repository("systemUserMapper")
public interface SystemUserMapper {
    /**
     * @param id 1 编号
     * @return int 成功返回大于1，失败返回小于1
     * @titel 公共根据编号，物理删除数据。
     * @description 公共根据编号，物理删除数据。
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:08
     */
    //int deleteByPrimaryKey(Long id);

    /**
     * @param row 1 用户实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int delete(SystemUser row);

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
     * @return int 大于0成功，小于等于0失败
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
     * @param paramMap 1 参数泛型键值对集合
     * @return com.framework.model.system.SystemUser
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemUser selectByPrimaryKey(Map<String, Object> paramMap);

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.model.system.SystemUser
     * @Titel 公共根据条件查询数据
     * @description 公共根据条件查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemUser selectByParam(SystemUser row);

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
     * @return java.util.List<com.framework.model.system.vo.SystemUserVo>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemUserVo> findPageList(SystemUserVo row);

    /**
     * @param row 1 用户实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
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

}