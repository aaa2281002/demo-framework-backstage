package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.other.NumeralUtil;
import com.framework.dao.mapper.system.SystemUserMapper;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 用户业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemUserServiceImpl")
public class SystemUserServiceImpl extends BaseService implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    /**
     * @param record 1 用户实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemUser record) {
        return systemUserMapper.deleteList(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(SystemUser record) {
        return systemUserMapper.insert(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(SystemUser record) {
        return systemUserMapper.insertSelective(record);
    }

    /**
     * @param list 1 用户批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemUser> list) {
        return systemUserMapper.insertList(list);
    }

    /**
     * @param record 1 用户实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemUser record) {
        return systemUserMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemUser record) {
        return systemUserMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param list 1 用户批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemUser> list) {
        return systemUserMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
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
     * @param record 1 用户实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemUser record) {
        return systemUserMapper.isExist(record);
    }

    /**
     * @param loginName 1 登录账号
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 根据登录账户名称查询用户数据
     * @Description 根据登录账户名称查询用户数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 20:30
     */
    @Override
    public SystemUser queryForLoginName(String loginName) {
        return systemUserMapper.queryForLoginName(loginName);
    }

    /**
     * @param record 1 用户实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUser>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemUser> findPageList(SystemUser record) {
        return systemUserMapper.findPageList(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemUser record) {
        return systemUserMapper.findPageListCount(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemUser>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemUser> findByList(SystemUser record) {
        return systemUserMapper.findByList(record);
    }

    /**
     * @param record 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(SystemUser record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
        if (StringUtils.isEmpty(record.getPassword())) {
            return r.ResponseResultFail().setMsg("密码不能为空！");
        }
        if (!record.getPassword().equals(record.getConfirmPassword())) {
            return r.ResponseResultFail().setMsg("两次密码不相同！");
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemUser su = getUser();
        String password = MD5Util.MD5EncodeToPassword(record.getPassword());
        record.setPassword(password);
        Date date = new Date();
        Long userId = su.getId();
        record.setOperaterTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setUserLevel(su.getUserLevel() + NumeralUtil.POSITIVE_ONE);
        try {
            long is = this.insert(record);
            return is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO ? r.ResponseResultSuccess() : r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemUser record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null) {
            return r.ResponseResultFail();
        }
        SystemUser su = this.selectByPrimaryKey(record.getId());
        if (su == null) {
            return r.ResponseResultFail();
        }
        if (StringUtils.isNotEmpty(record.getPassword())) {
            if (!record.getPassword().equals(record.getConfirmPassword())) {
                return r.ResponseResultFail().setMsg("两次密码不相同！");
            }
            String password = MD5Util.MD5EncodeToPassword(record.getPassword());
            if (su.getPassword().equals(password)) {
                return r.ResponseResultFail().setMsg("新旧密码不能相同!");
            }
            record.setPassword(password);
        }
        //禁用后踢出用户
        if (record.getIsEnable().intValue() == NumeralUtil.POSITIVE_TWO) {
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
        if (record.getUserLevel() == null) {
            record.setUserLevel(su.getUserLevel());
        } else {
            if (suser.getUserLevel().intValue() >= record.getUserLevel()) {
                return r.ResponseResultFail().setMsg("当前修改级别不能大于登录账户级别");
            }
        }

        record.setLoginName(null);
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            return is > NumeralUtil.POSITIVE_ZERO ? r.ResponseResultSuccess() : r.ResponseResultUpdateFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < 1) {
            return r.ResponseResultFail();
        }
        SystemUser record = new SystemUser();
        record.setIdList(idList);
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        try {
            int is = this.deleteList(record);
            return is > NumeralUtil.POSITIVE_ZERO ? r.ResponseResultSuccess() : r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param id        1 编号
     * @param loginName 2 账户名
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String loginName) {
        if (StringUtils.isEmpty(loginName)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemUser param = new SystemUser();
        param.setId(id);
        param.setLoginName(loginName);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemUser getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        SystemUser sm = this.selectByPrimaryKey(id);
        sm.setPassword(null);
        return sm;

    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询用户列表
     * @Description 本类后台管理根据条件分页查询用户列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemUser param) {
        ResponseResult r = this.getResponseResult();
        if (StringUtils.isNotEmpty(param.getSort()) && StringUtils.isNotEmpty(param.getOrder())) {
            param.setSort("p." + HumpOrLineUtil.humpToLine(param.getSort()));
        } else {
            param.setSort("p.create_Time");
            param.setOrder("desc");
        }
        SystemUser su = getUser();
        if (!super.authList.contains(su.getRoleCode())) {
            param.setGtNum(su.getUserLevel());
        }
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            int count = this.findPageListCount(param);
            List<SystemUser> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

}
