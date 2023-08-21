package com.framework.service.system.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.mapper.system.SystemRoleMapper;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemRoleMenu;
import com.framework.model.system.SystemUser;
import com.framework.model.system.SystemUserRole;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemRoleMenuService;
import com.framework.service.system.SystemRoleService;
import com.framework.service.system.SystemUserRoleService;
import com.framework.service.system.SystemUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 角色业务接口实现类
 * @datetime 2019/10/11
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
     * @param row 1 角色实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemRole row) {
        return systemRoleMapper.deleteList(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemRole row) {
        return systemRoleMapper.insertSelective(row);
    }

    /**
     * @param list 1 角色批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemRole> list) {
        return systemRoleMapper.insertList(list);
    }

    /**
     * @param row 1 角色实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemRole row) {
        return systemRoleMapper.updateByPrimaryKeySelective(row);
    }


    /**
     * @param list 1 角色批量修改集合
     * @return int 返回成功数量
     * @titel 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemRole> list) {
        return systemRoleMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemRole
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemRole selectByPrimaryKey(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        SystemUser su = getUser();
        String roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
        int level = NumeralUtil.POSITIVE_ONE_MILLION;
        if (ObjectUtils.isNotEmpty(roleStr)) {
            SystemRole sr = JSONObject.parseObject(roleStr, SystemRole.class);
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
     * @param row 1 角色实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemRole row) {
        return systemRoleMapper.isExist(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return java.util.List<com.framework.model.system.SystemRole>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemRole> findPageList(SystemRole row) {
        return systemRoleMapper.findPageList(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemRole row) {
        return systemRoleMapper.findPageListCount(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return java.util.List<com.framework.model.system.SystemRole>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemRole> findByList(SystemRole row) {
        return systemRoleMapper.findByList(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return java.util.List<com.framework.model.system.SystemRole>
     * @titel 根据条件查询初始化集合
     * @description 根据条件查询初始化集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemRole> findByInitList(SystemRole row) {
        return systemRoleMapper.findByInitList(row);
    }

    /**
     * @param row 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult save(SystemRole row) {
        ResponseResult r = getResponseResult();
        int num = systemRoleMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.fail();
        }
        SystemUser su = getUser();
        String roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
        if (roleStr == null) {
            return r.fail().setMsg("账户所属角色查询不到!");
        }
        SystemRole sr = JSONObject.parseObject(roleStr, SystemRole.class);
        int level = sr.getRoleLevel() + NumeralUtil.POSITIVE_ONE;
        Long userId = su.getId();
        Date date = new Date();
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        row.setOperaterTime(date);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setRoleLevel(level);
        systemRoleMapper.insertSelective(row);
        super.redisUtil.setAuthRoleString(row.getRoleCode(), JSONObject.toJSONString(row));
        return r.success();
    }

    /**
     * @param row 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
//    @Transactional(value = "readTransactionManager", rollbackFor = Exception.class)
    public ResponseResult edit(SystemRole row) {
        ResponseResult r = getResponseResult();
        int num = systemRoleMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.fail();
        }
        SystemRole sr = this.selectByPrimaryKey(row.getId());
        if (sr == null) {
            return r.fail().setMsg("修改角色信息不存在!");
        }
        if (row.getRoleLevel() == null) {
            row.setRoleLevel(sr.getRoleLevel());
        } else {
            SystemUser suser = getUser();
            String roleStr = redisUtil.getAuthRoleString(suser.getRoleCode());
            if (roleStr == null) {
                return r.fail();
            }
            SystemRole rsr = JSONObject.parseObject(roleStr, SystemRole.class);
            if (rsr.getRoleLevel().intValue() >= row.getRoleLevel()) {
                return r.fail().setMsg("当前级别不能大于所属角色级别");
            }
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        systemRoleMapper.updateByPrimaryKeySelective(row);
        //获取角色代码
        List<SystemRoleMenu> rmList = systemRoleMenuServiceImpl.findByRoleIdListMenuCode(sr.getId());
        List<String> menuCodeList = new ArrayList<String>();
        for (SystemRoleMenu srm : rmList) {
            menuCodeList.add(srm.getMenuCode());
        }
        row.setMenuCodeList(menuCodeList);
        //判断角色代码是否修改
        if (!sr.getRoleCode().equals(row.getRoleCode())) {
            //验证用户关联角色是否存在
            SystemUserRole systemUserRole = new SystemUserRole();
            List<Long> idList = new ArrayList<Long>();
            idList.add(row.getId());
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
//                    String obj = super.redisUtil.getLoginSystemUserString(su.getLoginName());
//                    if (StringUtils.isNotEmpty(obj)) {
//                        SystemUser isSU = JSONObject.parseObject(obj, SystemUser.class);
//                        su.setToken(isSU.getToken());
//                    }
                    su.setRoleCode(row.getRoleCode());
                    super.redisUtil.setLoginSystemUserString(su.getLoginName(), JSONObject.toJSONString(su));
                }
//                    }
            }
            //刷新用户的roleCode角色代码 结束
            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionRoleKey(sr.getRoleCode()));
        }
        super.redisUtil.setAuthRoleString(row.getRoleCode(), JSONObject.toJSONString(row));
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
            return r.fail().setMsg(sb.toString());
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
            return r.fail().setMsg(sb.toString());
        }
        SystemRole row = new SystemRole();
        row.setIdList(idList);
        List<SystemRole> roleList = systemRoleMapper.findByList(row);
        String[] delKey = new String[roleList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < roleList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionRoleKey(roleList.get(i).getRoleCode());
        }
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        systemRoleMapper.deleteList(row);
        if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
            super.redisUtil.deleteKey(delKey);
        }
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
        SystemRole row = new SystemRole();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        systemRoleMapper.delete(row);
        return r.success();
    }

    /**
     * @param id       1 编号
     * @param roleCode 2 角色代码
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String roleCode) {
        SystemRole param = new SystemRole();
        param.setId(id);
        param.setRoleCode(roleCode);
        Boolean is = systemRoleMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemRole
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemRole getByIdParam(Long id) {
        return this.selectByPrimaryKey(id);
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询角色列表
     * @description 本类后台管理根据条件分页查询角色列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemRole param) {
        ResponseResult r = super.getResponseResult();
        SystemUser su = getUser();
        if (!super.ignoredDataRoleAuth.getAuthList().contains(su.getRoleCode())) {
            String roleStr = redisUtil.getAuthRoleString(su.getRoleCode());
            if (roleStr == null) {
                return r.fail();
            }
            SystemRole sr = JSONObject.parseObject(roleStr, SystemRole.class);
            param.setGtaeNum(sr.getRoleLevel());
        }
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemRoleMapper.findPageListCount(param);
        List<SystemRole> list = systemRoleMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据用户编号查询角色列表
     * @description 根据用户编号查询角色列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findUserByRolePageList(SystemRole param) {
        param.setLimit(Integer.MAX_VALUE);
        param.setPage(NumeralUtil.POSITIVE_ONE);
        return findParamPageList(param);
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询角色列表
     * @description 根据条件查询角色列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:52
     */
    @Override
    public ResponseResult findParamList(SystemRole param) {
        ResponseResult r = super.getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        List<SystemRole> list = systemRoleMapper.findByList(param);
        return r.success().setData(list).setCount(Integer.MAX_VALUE);
    }
}
