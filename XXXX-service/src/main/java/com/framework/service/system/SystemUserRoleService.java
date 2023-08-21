package com.framework.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemUserRole;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system
 * @description 用户角色关联业务接口类
 * @datetime 2019/10/11
 */
public interface SystemUserRoleService {
    /**
     * @param row 1 用户角色关联类实体对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据用户编号，用户编号集合物理批量删除
     * @description 公共根据用户编号，用户编号集合物理批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:58
     */
    int deleteList(SystemUserRole row);

    /**
     * @param userId 1 用户编号
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据用户编号物理删除
     * @description 公共根据用户编号物理删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:59
     */
    int deleteUserId(Long userId);

    /**
     * @param row 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共添加
     * @description 公共添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insert(SystemUserRole row);

    /**
     * @param row 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemUserRole row);

    /**
     * @param list 1 用户角色批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    int insertList(List<SystemUserRole> list);

    /**
     * @param row 1 用户角色实体类对象
     * @return java.util.List<com.framework.model.system.SystemUserRole>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemUserRole> findByList(SystemUserRole row);

    /**
     * @param row 1 用户角色关联类对象
     * @return java.util.List<com.framework.model.system.SystemUserRole>
     * @titel 公共根据条件查询存在集合
     * @description 公共根据条件查询存在集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 17:11
     */
    List<SystemUserRole> findByIsExistList(SystemUserRole row);

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(Long userId, List<Long> roleIdList);

    /**
     * @param param 1 用户角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询集合关联选中方法
     * @description 根据条件查询集合关联选中方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 19:42
     */
    ResponseResult findByParamList(SystemUserRole param);

    /**
     * @param roleCode 1 角色代码
     * @return com.framework.common.response.ResponseResult
     * @Title 根据角色代码获取用户集合
     * @Description 根据角色代码获取用户集合
     * @Author 龘鵺
     * @DateTime 2023/5/16 11:39
     */
    ResponseResult findByRoleCodeList(String roleCode);
}
