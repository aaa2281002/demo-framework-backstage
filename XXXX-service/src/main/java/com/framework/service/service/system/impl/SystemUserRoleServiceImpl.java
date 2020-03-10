package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.dao.mapper.system.SystemUserRoleMapper;
import com.framework.model.entity.system.SystemUser;
import com.framework.model.entity.system.SystemUserRole;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemRoleService;
import com.framework.service.service.system.SystemUserRoleService;
import com.framework.service.service.system.SystemUserService;
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
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 用户角色关联业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemUserRoleServiceImpl")
public class SystemUserRoleServiceImpl extends BaseService implements SystemUserRoleService {
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    private SystemUserService systemUserServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @param record 1 用户角色关联类实体对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据用户编号，角色编号集合物理批量删除
     * @Description 公共根据用户编号，角色编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:58
     */
    @Override
    public int deleteList(SystemUserRole record) {
        return systemUserRoleMapper.deleteList(record);
    }

    /**
     * @param userId 1 用户编号
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据用户编号物理删除
     * @Description 公共根据用户编号物理删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:59
     */
    @Override
    public int deleteUserId(Long userId) {
        return systemUserRoleMapper.deleteUserId(userId);
    }

    /**
     * @param record 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemUserRole record) {
        return systemUserRoleMapper.insert(record);
    }


    /**
     * @param record 1 用户角色实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemUserRole record) {
        return systemUserRoleMapper.insertSelective(record);
    }

    /**
     * @param list 1 用户角色批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemUserRole> list) {
        return systemUserRoleMapper.insertList(list);
    }


    /**
     * @param record 1 用户角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUserRole>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemUserRole> findByList(SystemUserRole record) {
        return systemUserRoleMapper.findByList(record);
    }

    /**
     * @param record 1 用户角色关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUserRole>
     * @Titel 根据条件查询存在集合
     * @Description 根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:11
     */
    @Override
    public List<SystemUserRole> findByIsExistList(SystemUserRole record) {
        return systemUserRoleMapper.findByIsExistList(record);
    }

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(Long userId, List<Long> roleIdList) {
        ResponseResult r = getResponseResult();
        if (userId == null || userId.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        SystemUser su = systemUserServiceImpl.selectByPrimaryKey(userId);
        if (su == null) {
            return r.ResponseResultFail().setMsg("用户不存在!");
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
            try {
                this.deleteUserId(userId);
//                redisUtil.setLoginSystemUserString(su.getLoginName(), su);
                return r.ResponseResultSuccess();
            } catch (Exception e) {
                return r.ResponseResultFail().setMsg("用户角色保存异常!");
            }
        }
        List<SystemUserRole> addList = new ArrayList<SystemUserRole>();
        //处理添加 删除业务逻辑循环
        for (Long roleId : roleIdList) {
            SystemUserRole sur = new SystemUserRole();
            sur.setUserId(userId);
            sur.setRoleId(roleId);
            addList.add(sur);
        }
        try {
            this.deleteUserId(userId);//删除方法必须在添加方法之前执行。不然会出现一些数据没删除的问题
            this.insertList(addList);
            return r.ResponseResultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError().setMsg("用户角色保存异常!");
        }
//        SystemRole systemRole = new SystemRole();
//        systemRole.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
//        List<SystemRole> roleList = systemRoleServiceImpl.findByList(systemRole);
//        for (SystemRole sr : roleList) {
//            if (roleIdList.contains(sr.getId())) {
//                su.setRoleCode(sr.getRoleCode());
//                break;
//            }
//        }
//        try {
//            //更新Redis缓存
//            redisUtil.setLoginSystemUserString(su.getLoginName(), su);
//        } catch (Exception e) {
//            return r.ResponseResultFail().setMsg("用户角色缓存异常!");
//        }
    }

    /**
     * @param param 1 用户角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询集合关联选中方法
     * @Description 根据条件查询集合关联选中方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 19:42
     */
    @Override
    public ResponseResult findByParamList(SystemUserRole param) {
        ResponseResult rr = getResponseResult();
        if (param == null || param.getUserId() == null || param.getUserId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return rr.ResponseResultFail();
        }
        try {
            List<SystemUserRole> list = this.findByList(param);
            return rr.ResponseResultSuccess().setData((list != null && list.size() > NumeralUtil.POSITIVE_ZERO) ? list.get(NumeralUtil.POSITIVE_ZERO) : null);
        } catch (Exception e) {
            e.printStackTrace();
            return rr.ResponseResultError();
        }
    }
}
