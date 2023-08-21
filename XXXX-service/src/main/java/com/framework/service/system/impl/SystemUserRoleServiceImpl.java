package com.framework.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.mapper.system.SystemUserRoleMapper;
import com.framework.model.system.SystemUser;
import com.framework.model.system.SystemUserRole;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemUserRoleService;
import com.framework.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 用户角色关联业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemUserRoleServiceImpl")
public class SystemUserRoleServiceImpl extends BaseService implements SystemUserRoleService {
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    private SystemUserService systemUserServiceImpl;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @param row 1 用户角色关联类实体对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据用户编号，角色编号集合物理批量删除
     * @description 公共根据用户编号，角色编号集合物理批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:58
     */
    @Override
    public int deleteList(SystemUserRole row) {
        return systemUserRoleMapper.deleteList(row);
    }

    /**
     * @param userId 1 用户编号
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据用户编号物理删除
     * @description 公共根据用户编号物理删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:59
     */
    @Override
    public int deleteUserId(Long userId) {
        return systemUserRoleMapper.deleteUserId(userId);
    }

    /**
     * @param row 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共添加
     * @description 公共添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemUserRole row) {
        return systemUserRoleMapper.insert(row);
    }


    /**
     * @param row 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemUserRole row) {
        return systemUserRoleMapper.insertSelective(row);
    }

    /**
     * @param list 1 用户角色批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemUserRole> list) {
        return systemUserRoleMapper.insertList(list);
    }


    /**
     * @param row 1 用户角色实体类对象
     * @return java.util.List<com.framework.model.system.SystemUserRole>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemUserRole> findByList(SystemUserRole row) {
        return systemUserRoleMapper.findByList(row);
    }

    /**
     * @param row 1 用户角色关联类对象
     * @return java.util.List<com.framework.model.system.SystemUserRole>
     * @titel 根据条件查询存在集合
     * @description 根据条件查询存在集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 17:11
     */
    @Override
    public List<SystemUserRole> findByIsExistList(SystemUserRole row) {
        return systemUserRoleMapper.findByIsExistList(row);
    }

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(Long userId, List<Long> roleIdList) {
        ResponseResult r = getResponseResult();
        SystemUser su = systemUserServiceImpl.selectByPrimaryKey(userId);
        if (su == null) {
            return r.fail().setMsg("用户不存在!");
        }
        //修改后踢出用户
        // 查询用户的 Session 信息，返回值 key 为 sessionId
        Map<String, ? extends Session> userSessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, su.getLoginName());
        if (userSessions.size() > NumeralUtil.POSITIVE_ZERO) {
            // 移除用户的 session 信息
            Set<String> sessionIds = userSessions.keySet();
            if (sessionIds.size() > NumeralUtil.POSITIVE_ZERO) {
                for (String session : sessionIds) {
                    redisIndexedSessionRepository.deleteById(session);
                }
            }
        }
        if (roleIdList == null) {
            systemUserRoleMapper.deleteUserId(userId);
//                redisUtil.setLoginSystemUserString(su.getLoginName(), su);
            return r.success();
        }
        List<SystemUserRole> addList = new ArrayList<SystemUserRole>();
        //处理添加 删除业务逻辑循环
        for (Long roleId : roleIdList) {
            SystemUserRole sur = new SystemUserRole();
            sur.setUserId(userId);
            sur.setRoleId(roleId);
            addList.add(sur);
        }
        systemUserRoleMapper.deleteUserId(userId);//删除方法必须在添加方法之前执行。不然会出现一些数据没删除的问题
        systemUserRoleMapper.insertList(addList);
        return r.success();
//        SystemRole systemRole = new SystemRole();
//        systemRole.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
//        List<SystemRole> roleList = systemRoleServiceImpl.findByList(systemRole);
//        for (SystemRole sr : roleList) {
//            if (roleIdList.contains(sr.getId())) {
//                su.setRoleCode(sr.getRoleCode());
//                break;
//            }
//        }
//            //更新Redis缓存
//            redisUtil.setLoginSystemUserString(su.getLoginName(), su);
//            return r.fail().setMsg("用户角色缓存异常!");
    }

    /**
     * @param userId 1 用户编号
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询集合关联选中方法
     * @description 根据条件查询集合关联选中方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 19:42
     */
    @Override
    public SystemUserRole findByParamList(Long userId) {
        SystemUserRole row = new SystemUserRole();
        row.setUserId(userId);
        List<SystemUserRole> list = systemUserRoleMapper.findByList(row);
        return (list != null && list.size() > NumeralUtil.POSITIVE_ZERO) ? list.get(NumeralUtil.POSITIVE_ZERO) : null;
    }
}
