package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemUserRole;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 用户角色关联业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemUserRoleService {
    /**
     * @param record 1 用户角色关联类实体对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据用户编号，用户编号集合物理批量删除
     * @Description 公共根据用户编号，用户编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:58
     */
    int deleteList(SystemUserRole record);

    /**
     * @param userId 1 用户编号
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据用户编号物理删除
     * @Description 公共根据用户编号物理删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:59
     */
    int deleteUserId(Long userId);

    /**
     * @param record 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insert(SystemUserRole record);

    /**
     * @param record 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    int insertSelective(SystemUserRole record);

    /**
     * @param list 1 用户角色批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemUserRole> list);

    /**
     * @param record 1 用户角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUserRole>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemUserRole> findByList(SystemUserRole record);

    /**
     * @param record 1 用户角色关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUserRole>
     * @Titel 公共根据条件查询存在集合
     * @Description 公共根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:11
     */
    List<SystemUserRole> findByIsExistList(SystemUserRole record);

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(Long userId, List<Long> roleIdList);

    /**
     * @param param 1 用户角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询集合关联选中方法
     * @Description 根据条件查询集合关联选中方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 19:42
     */
    public ResponseResult findByParamList(SystemUserRole param);
}
