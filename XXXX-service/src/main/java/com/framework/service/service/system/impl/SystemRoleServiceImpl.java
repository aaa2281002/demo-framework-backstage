package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemRoleMapper;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemRoleMenu;
import com.framework.model.entity.system.SystemUser;
import com.framework.model.entity.system.SystemUserRole;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemRoleMenuService;
import com.framework.service.service.system.SystemRoleService;
import com.framework.service.service.system.SystemUserRoleService;
import com.framework.service.service.system.SystemUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 角色业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemRoleServiceImpl")
public class SystemRoleServiceImpl extends BaseService implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Autowired
    private SystemUserRoleService systemUserRoleServiceImpl;
    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;
    @Autowired
    private SystemUserService systemUserServiceImpl;

    /**
     * @param record 1 角色实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemRole record) {
        return systemRoleMapper.deleteList(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(SystemRole record) {
        return systemRoleMapper.insert(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(SystemRole record) {
        return systemRoleMapper.insertSelective(record);
    }

    /**
     * @param list 1 角色批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemRole> list) {
        return systemRoleMapper.insertList(list);
    }

    /**
     * @param record 1 角色实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemRole record) {
        return systemRoleMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemRole record) {
        return systemRoleMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * @param list 1 角色批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemRole> list) {
        return systemRoleMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemRole
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemRole selectByPrimaryKey(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        SystemUser su = getUser();
        Object roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
        int level = NumeralUtil.POSITIVE_ONE_MILLION;
        if (ObjectUtils.isNotEmpty(roleStr)) {
            SystemRole sr = (SystemRole) roleStr;
            level = sr.getRoleLevel();
        }
        if (level > NumeralUtil.POSITIVE_ZERO) {
            map.put("gtNum", level);
        } else {
            map.put("gtaeNum", level);
        }
        return systemRoleMapper.selectByPrimaryKey(map);
    }

    /**
     * @param record 1 角色实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemRole record) {
        return systemRoleMapper.isExist(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRole>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemRole> findPageList(SystemRole record) {
        return systemRoleMapper.findPageList(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemRole record) {
        return systemRoleMapper.findPageListCount(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRole>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemRole> findByList(SystemRole record) {
        return systemRoleMapper.findByList(record);
    }

    /**
     * @param record 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(SystemRole record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemUser su = getUser();
        Object roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
        if (roleStr == null) {
            return r.ResponseResultFailRepeat().setMsg("账户所属角色查询不到!");
        }
        SystemRole sr = (SystemRole) roleStr;
        int level = sr.getRoleLevel() + NumeralUtil.POSITIVE_ONE;
        Long userId = su.getId();
        Date date = new Date();
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        record.setOperaterTime(date);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setRoleLevel(level);
        try {
            long is = this.insert(record);
            if (is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
                super.redisUtil.setAuthRoleString(record.getRoleCode(), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
//    @Transactional(value = "readTransactionManager", rollbackFor = Exception.class)
    public ResponseResult edit(SystemRole record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemRole sr = this.selectByPrimaryKey(record.getId());
        if (record.getRoleLevel() == null) {
            record.setRoleLevel(sr.getRoleLevel());
        } else {
            SystemUser suser = getUser();
            Object roleStr = redisUtil.getAuthRoleString(suser.getRoleCode());
            if (roleStr == null) {
                return r.ResponseResultFail();
            }
            SystemRole rsr = (SystemRole) roleStr;
            if (rsr.getRoleLevel().intValue() >= record.getRoleLevel()) {
                return r.ResponseResultFail().setMsg("当前级别不能大于所属角色级别");
            }
        }
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                //获取角色代码
                List<SystemRoleMenu> rmList = systemRoleMenuServiceImpl.findByRoleIdListMenuCode(sr.getId());
                List<String> menuCodeList = new ArrayList<String>();
                for (SystemRoleMenu srm : rmList) {
                    menuCodeList.add(srm.getMenuCode());
                }
                record.setMenuCodeList(menuCodeList);
                //判断角色代码是否修改
                if (!sr.getRoleCode().equals(record.getRoleCode())) {
                    //验证用户关联角色是否存在
                    SystemUserRole systemUserRole = new SystemUserRole();
                    List<Long> idList = new ArrayList<Long>();
                    idList.add(record.getId());
                    systemUserRole.setIdList(idList);
                    systemUserRole.setRoleId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
                    List<SystemUserRole> surList = systemUserRoleServiceImpl.findByIsExistList(systemUserRole);
                    if (surList != null && surList.size() > NumeralUtil.POSITIVE_ZERO) {
                        //刷新用户的roleCode角色代码 开始
                        List<Long> userIdList = new ArrayList<Long>();
                        for (SystemUserRole ur : surList) {
                            userIdList.add(ur.getUserId());
                        }
//                    if(userIdList.size() > 0){
                        //查询用户集合
                        SystemUser systemUser = new SystemUser();
                        systemUser.setIdList(userIdList);
                        List<SystemUser> suList = systemUserServiceImpl.findByList(systemUser);
                        for (SystemUser su : suList) {
                            Object obj = super.redisUtil.getLoginSystemUserString(su.getLoginName());
//                        if (obj != null) {
//                            SystemUser isSU = (SystemUser) obj;
//                            su.setToken(isSU.getToken());
//                        }
                            su.setRoleCode(record.getRoleCode());
                            super.redisUtil.setLoginSystemUserString(su.getLoginName(), su);
                        }
//                    }
                    }
                    //刷新用户的roleCode角色代码 结束
                    super.redisUtil.deleteKey(RedisKeyUtil.getPermissionRoleKey(sr.getRoleCode()));
                }
                super.redisUtil.setAuthRoleString(record.getRoleCode(), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultUpdateFail();
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
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        //验证角色关联角色是否存在
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setRoleId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        systemRoleMenu.setIdList(idList);
        List<SystemRoleMenu> srbList = systemRoleMenuServiceImpl.findByIsExistList(systemRoleMenu);
        if (srbList != null && srbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除角色菜单关联管理以下关联角色:");
            for (SystemRoleMenu srm : srbList) {
                if (sb.length() > NumeralUtil.POSITIVE_NINETEEN) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(srm.getRoleName());
                } else {
                    sb.append(srm.getRoleName());
                }
            }
            return r.ResponseResultFail().setMsg(sb.toString());
        }
        //验证用户关联角色是否存在
        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setIdList(idList);
        systemUserRole.setRoleId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        List<SystemUserRole> suList = systemUserRoleServiceImpl.findByIsExistList(systemUserRole);
        if (suList != null && suList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除用户角色关联管理以下关联用户:");
            for (SystemUserRole su : suList) {
                if (sb.length() > NumeralUtil.POSITIVE_NINETEEN) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(su.getLoginName());
                } else {
                    sb.append(su.getLoginName());
                }
            }
            return r.ResponseResultFail().setMsg(sb.toString());
        }
        SystemRole record = new SystemRole();
        record.setIdList(idList);
        List<SystemRole> roleList = this.findByList(record);
        String[] delKey = new String[roleList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < roleList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionRoleKey(roleList.get(i).getRoleCode());
        }
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        try {
            int is = this.deleteList(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
                    this.redisUtil.deleteKey(delKey);
                }
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param id       1 编号
     * @param roleCode 2 角色代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String roleCode) {
        if (StringUtils.isEmpty(roleCode)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemRole param = new SystemRole();
        param.setId(id);
        param.setRoleCode(roleCode);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemRole
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemRole getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        return this.selectByPrimaryKey(id);
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询角色列表
     * @Description 本类后台管理根据条件分页查询角色列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemRole param) {
        ResponseResult r = this.getResponseResult();
        if (StringUtils.isNotEmpty(param.getSort()) && StringUtils.isNotEmpty(param.getOrder())) {
            param.setSort("p." + HumpOrLineUtil.humpToLine(param.getSort()));
        } else {
            param.setSort("p.create_Time");
            param.setOrder("desc");
        }
        SystemUser su = getUser();
        if (!super.authList.contains(su.getRoleCode())) {
            Object roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
            if (roleStr == null) {
                return r.ResponseResultFail();
            }
            SystemRole sr = (SystemRole) roleStr;
            param.setGtaeNum(sr.getRoleLevel());
        }
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            int count = this.findPageListCount(param);
            List<SystemRole> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询角色列表
     * @Description 根据条件查询角色列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:52
     */
    @Override
    public ResponseResult findParamList(SystemRole param) {
        ResponseResult r = this.getResponseResult();
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            List<SystemRole> list = this.findByList(param);
            return r.ResponseResultSuccess().setData(list).setCount(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }
}
