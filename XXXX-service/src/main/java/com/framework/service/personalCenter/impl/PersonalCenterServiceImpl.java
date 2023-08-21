package com.framework.service.personalCenter.impl;

import com.framework.common.exception.user.LoginFailLimitException;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.encrypt.PasswordUtil;
import com.framework.common.util.encrypt.RSAEncrypt;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.personalCenter.PersonalCenterService;
import com.framework.service.system.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.personalCenter.impl
 * @description 用户登录个人中心业务接口实现类
 * @date 2020/1/2 17:15
 */
@Service("personalCenterServiceImpl")
public class PersonalCenterServiceImpl extends BaseService implements PersonalCenterService {
    private Logger log = LoggerFactory.getLogger(PersonalCenterServiceImpl.class);
    @Autowired
    private SystemUserService systemUserServiceImpl;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @return com.framework.model.system.SystemUser
     * @titel 获取当前登录用户信息
     * @description 获取当前登录用户信息
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:40
     */
    @Override
    public SystemUser getLoginNameUser() {
        SystemUser systemUser = systemUserServiceImpl.selectByParam(getUserId());
        return systemUser;
    }

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人密码修改
     * @description 用户个人密码修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:39
     */
    @Override
    public ResponseResult edit(String oldPassword, String password, String confirmPassword) {
        SystemUser su = getUser();
        try {
            oldPassword = RSAEncrypt.decryptPrivate(oldPassword, PasswordUtil.privateKey);
            password = RSAEncrypt.decryptPrivate(password, PasswordUtil.privateKey);
            confirmPassword = RSAEncrypt.decryptPrivate(confirmPassword, PasswordUtil.privateKey);
        } catch (Exception e) {
            log.error("save:解密错误:{}", su.getLoginName());
            throw new LoginFailLimitException("解密错误！");
        }
        String oldPW = MD5Util.MD5EncodeToPassword(oldPassword);
        if (!su.getPassword().equals(oldPW)) {
            return getResponseResult().fail().setMsg("旧密码错误!");
        }
        String p = MD5Util.MD5EncodeToPassword(password);
        if (p.equals(oldPW)) {
            return getResponseResult().fail().setMsg("新旧密码不相同!");
        }
        if (!password.equals(confirmPassword)) {
            return getResponseResult().fail().setMsg("确认密码不相同!");
        }
        if (password.length() < NumeralUtil.POSITIVE_SIX || password.length() > NumeralUtil.POSITIVE_TWENTY_FIVE) {
            return getResponseResult().fail().setMsg("密码最少6至最多25个字符！");
        }
        SystemUser param = new SystemUser();
        param.setPassword(p);
        param.setId(getUserId());
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
        return getResponseResult().success().setMsg("修改成功,请重新登录!");
    }

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人信息修改
     * @description 用户个人信息修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:41
     */
    @Override
    public ResponseResult edit(SystemUser p) {
        SystemUser param = new SystemUser();
        param.setName(p.getName());
        param.setEmail(p.getEmail());
        param.setTelphone(p.getTelphone());
        param.setId(getUserId());
        systemUserServiceImpl.updateByPrimaryKeySelective(param);
        return getResponseResult().success().setMsg("修改成功!");
    }
}
