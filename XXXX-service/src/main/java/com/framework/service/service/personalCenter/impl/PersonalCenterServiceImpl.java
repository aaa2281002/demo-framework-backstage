package com.framework.service.service.personalCenter.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.service.personalCenter.PersonalCenterService;
import com.framework.service.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.personalCenter.impl
 * @Description 用户登录个人中心业务接口实现类
 * @Date 2020/1/2 17:15
 * @Version 1.0
 */
@Service
public class PersonalCenterServiceImpl extends BaseService implements PersonalCenterService {
    @Autowired
    private SystemUserService systemUserServiceImpl;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 获取当前登录用户信息
     * @Description 获取当前登录用户信息
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:40
     */
    @Override
    public SystemUser getLoginNameUser() {
        return systemUserServiceImpl.selectByPrimaryKey(getUserId());
    }

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人密码修改
     * @Description 用户个人密码修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:39
     */
    @Override
    public ResponseResult edit(String oldPassword, String password, String confirmPassword) {
        SystemUser su = getUser();
        String oldPW = MD5Util.MD5EncodeToPassword(oldPassword);
        if (!su.getPassword().equals(oldPW)) {
            return getResponseResult().ResponseResultFail().setMsg("旧密码错误!");
        }
        String p = MD5Util.MD5EncodeToPassword(password);
        if (p.equals(oldPW)) {
            return getResponseResult().ResponseResultFail().setMsg("新旧密码不相同!");
        }
        if (!password.equals(confirmPassword)) {
            return getResponseResult().ResponseResultFail().setMsg("确认密码不相同!");
        }

        SystemUser param = new SystemUser();
        param.setPassword(p);
        param.setId(getUserId());
        try {
            systemUserServiceImpl.updateByPrimaryKeySelective(param);
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
            return getResponseResult().ResponseResultSuccess().setMsg("修改成功,请重新登录!");
        } catch (Exception e) {
            e.printStackTrace();
            return getResponseResult().ResponseResultFail().setMsg("系统内部错误!");
        }
    }

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人信息修改
     * @Description 用户个人信息修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:41
     */
    @Override
    public ResponseResult edit(SystemUser p) {
        SystemUser param = new SystemUser();
        param.setEmail(p.getEmail());
        param.setTelphone(p.getTelphone());
        param.setId(getUserId());
        try {
            systemUserServiceImpl.updateByPrimaryKeySelective(param);
            return getResponseResult().ResponseResultSuccess().setMsg("修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return getResponseResult().ResponseResultFail().setMsg("系统内部错误!");
        }
    }
}
