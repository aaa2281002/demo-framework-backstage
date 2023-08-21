package com.framework.service.system.impl;

import com.framework.common.exception.user.LoginFailLimitException;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.encrypt.PasswordUtil;
import com.framework.common.util.encrypt.RSAEncrypt;
import com.framework.common.util.other.NumeralUtil;
import com.framework.mapper.system.SystemUserMapper;
import com.framework.model.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 用户业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemUserServiceImpl")
public class SystemUserServiceImpl extends BaseService implements SystemUserService {
    private Logger log = LoggerFactory.getLogger(SystemUserServiceImpl.class);
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @param row 1 用户实体类对象
     * @return int 大于等于1成功， 0失败
     * @title 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemUser row) {
        return systemUserMapper.deleteList(row);
    }

    /**
     * @param row 1 用户实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @title 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemUser row) {
        return systemUserMapper.insertSelective(row);
    }

    /**
     * @param list 1 用户批量添加集合
     * @return int 返回成功数量
     * @title 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemUser> list) {
        return systemUserMapper.insertList(list);
    }

    /**
     * @param row 1 用户实体类对象
     * @return int 0失败，1成功
     * @title 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemUser row) {
        return systemUserMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param list 1 用户批量修改集合
     * @return int 返回成功数量
     * @title 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemUser> list) {
        return systemUserMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @title 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemUser selectByPrimaryKey(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        if (getUser().getUserLevel().intValue() > NumeralUtil.POSITIVE_ZERO) {
            map.put("gtNum", getUser().getUserLevel());
        } else {
            map.put("gtaeNum", getUser().getUserLevel());
        }
        return systemUserMapper.selectByPrimaryKey(map);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @title 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemUser selectByParam(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return systemUserMapper.selectByPrimaryKey(map);
    }

    /**
     * @param row 1 用户实体类对象
     * @return int 0不存在， 大于等于1存在
     * @title 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemUser row) {
        return systemUserMapper.isExist(row);
    }

    /**
     * @param loginName 1 登录账号
     * @return com.framework.model.system.SystemUser
     * @title 根据登录账户名称查询用户数据
     * @description 根据登录账户名称查询用户数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 20:30
     */
    @Override
    public SystemUser queryForLoginName(String loginName) {
        return systemUserMapper.queryForLoginName(loginName);
    }

    /**
     * @param row 1 用户实体类对象
     * @return java.util.List<com.framework.model.system.SystemUser>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemUser> findPageList(SystemUser row) {
        return systemUserMapper.findPageList(row);
    }

    /**
     * @param row 1 用户实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemUser row) {
        return systemUserMapper.findPageListCount(row);
    }

    /**
     * @param row 1 用户实体类对象
     * @return java.util.List<com.framework.model.system.SystemUser>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemUser> findByList(SystemUser row) {
        return systemUserMapper.findByList(row);
    }

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(SystemUser row) {
        ResponseResult r = getResponseResult();
        try {
            row.setPassword(RSAEncrypt.decryptPrivate(row.getPassword(), PasswordUtil.privateKey));
            row.setConfirmPassword(RSAEncrypt.decryptPrivate(row.getConfirmPassword(), PasswordUtil.privateKey));
        } catch (Exception e) {
            log.error("save:解密错误:{}", row.getLoginName());
            throw new LoginFailLimitException("解密错误！");
        }
        if (row.getPassword().length() < NumeralUtil.POSITIVE_SIX || row.getPassword().length() > NumeralUtil.POSITIVE_TWENTY_FIVE) {
            return r.fail().setMsg("密码最少6至最多25个字符！");
        }
        if (!row.getPassword().equals(row.getConfirmPassword())) {
            return r.fail().setMsg("两次密码不相同！");
        }
        int num = systemUserMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.fail();
        }
        SystemUser su = getUser();
        String password = MD5Util.MD5EncodeToPassword(row.getPassword());
        row.setPassword(password);
        Date date = new Date();
        Long userId = su.getId();
        row.setOperaterTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setUserLevel(su.getUserLevel() + NumeralUtil.POSITIVE_ONE);
        systemUserMapper.insertSelective(row);
        return r.success();
    }

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemUser row) {
        ResponseResult r = getResponseResult();
        SystemUser su = this.selectByPrimaryKey(row.getId());
        if (su == null) {
            return r.fail().setMsg("修改用户信息不存在!");
        }
//        if (StringUtils.isNotEmpty(row.getPassword())) {
//            if (row.getPassword().length() < NumeralUtil.POSITIVE_SIX || row.getPassword().length() > NumeralUtil.POSITIVE_TWENTY_FIVE) {
//                return r.fail().setMsg("密码最少6至最多25个字符");
//            }
//            if (!row.getPassword().equals(row.getConfirmPassword())) {
//                return r.fail().setMsg("两次密码不相同！");
//            }
//            String password = MD5Util.MD5EncodeToPassword(row.getPassword());
//            if (su.getPassword().equals(password)) {
//                return r.fail().setMsg("新旧密码不能相同!");
//            }
//            row.setPassword(password);
//        }
        //禁用后踢出用户
        if (row.getIsEnable().intValue() == NumeralUtil.POSITIVE_TWO) {
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
        }
        SystemUser suser = getUser();
        if (row.getUserLevel() == null) {
            row.setUserLevel(su.getUserLevel());
        } else {
            if (suser.getUserLevel().intValue() >= row.getUserLevel()) {
                return r.fail().setMsg("当前修改级别不能大于登录账户级别");
            }
        }

        row.setLoginName(null);
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setPassword(null);
        systemUserMapper.updateByPrimaryKeySelective(row);
        return r.success();
    }

    /**
     * @param row 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改密码
     * @description 修改密码
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult password(SystemUser row) {
        ResponseResult r = getResponseResult();
        try {
            row.setPassword(RSAEncrypt.decryptPrivate(row.getPassword(), PasswordUtil.privateKey));
            row.setConfirmPassword(RSAEncrypt.decryptPrivate(row.getConfirmPassword(), PasswordUtil.privateKey));
        } catch (Exception e) {
            log.error("save:解密错误:{}", row.getLoginName());
            throw new LoginFailLimitException("解密错误！");
        }
        if (row.getPassword().length() < NumeralUtil.POSITIVE_SIX || row.getPassword().length() > NumeralUtil.POSITIVE_TWENTY_FIVE) {
            return r.fail().setMsg("密码最少6至最多25个字符！");
        }
        SystemUser su = this.selectByPrimaryKey(row.getId());
        if (su == null) {
            return r.fail().setMsg("修改用户信息不存在!");
        }
        if (!row.getPassword().equals(row.getConfirmPassword())) {
            return r.fail().setMsg("两次密码不相同！");
        }
        String password = MD5Util.MD5EncodeToPassword(row.getPassword());
        if (su.getPassword().equals(password)) {
            return r.fail().setMsg("新旧密码不能相同!");
        }
        SystemUser param = new SystemUser();
        Date date = new Date();
        Long userId = getUserId();
        param.setId(row.getId());
        param.setOperaterId(userId);
        param.setOperaterTime(date);
        param.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        param.setPassword(password);
        systemUserMapper.updateByPrimaryKeySelective(param);
        return r.success();
    }

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.fail();
        }
        SystemUser row = new SystemUser();
        row.setIdList(idList);
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        systemUserMapper.deleteList(row);
        return r.success();
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id) {
        ResponseResult r = getResponseResult();
        SystemUser row = new SystemUser();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        systemUserMapper.delete(row);
        return r.success();
    }

    /**
     * @param id        1 编号
     * @param loginName 2 账户名
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String loginName) {
        SystemUser param = new SystemUser();
        param.setId(id);
        param.setLoginName(loginName);
        Boolean is = systemUserMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemUser
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemUser getByIdParam(Long id) {
        SystemUser p = this.selectByPrimaryKey(id);
        p.setPassword(null);
        return p;
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询用户列表
     * @description 本类后台管理根据条件分页查询用户列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemUser param) {
        ResponseResult r = super.getResponseResult();
        SystemUser su = getUser();
        if (!super.ignoredDataRoleAuth.getAuthList().contains(su.getRoleCode())) {
            param.setGtNum(su.getUserLevel());
        }
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemUserMapper.findPageListCount(param);
        List<SystemUser> list = systemUserMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @return boolean
     * @title 验证用户是否为超级管理员权限
     * @description 验证用户是否为超级管理员权限
     * @author 龘鵺
     * @dateTime 2023/6/2 11:23
     */
    @Override
    public boolean isAuth() {
        SystemUser su = super.getUser();
        return super.ignoredRoleAuth.getAuthList().contains(su.getRoleCode());
    }

}
