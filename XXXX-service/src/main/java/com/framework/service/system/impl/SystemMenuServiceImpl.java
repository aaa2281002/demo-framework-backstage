package com.framework.service.system.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.enums.menu.MenuTargetEnum;
import com.framework.common.enums.result.ResultEnum;
import com.framework.common.model.MenuUtil;
import com.framework.common.model.Tree;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.TreeUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisPrefixUtil;
import com.framework.mapper.system.SystemMenuMapper;
import com.framework.mapper.system.SystemRoleMenuMapper;
import com.framework.model.system.SystemMenu;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemRoleMenu;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemMenuService;
import com.framework.service.system.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 菜单业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemMenuServiceImpl")
public class SystemMenuServiceImpl extends BaseService implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    /**
     * @param row 1 菜单实体类对象
     * @return int 大于等于1成功， 0失败
     * @title 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemMenu row) {
        return systemMenuMapper.deleteList(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @title 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemMenu row) {
        return systemMenuMapper.insertSelective(row);
    }

    /**
     * @param list 1 菜单批量添加集合
     * @return int 返回成功数量
     * @title 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemMenu> list) {
        return systemMenuMapper.insertList(list);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return int 0失败，1成功
     * @title 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemMenu row) {
        return systemMenuMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param list 1 菜单批量修改集合
     * @return int 返回成功数量
     * @title 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemMenu> list) {
        return systemMenuMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemMenu
     * @title 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return int 0不存在， 大于等于1存在
     * @title 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemMenu row) {
        return systemMenuMapper.isExist(row);
    }


    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @title 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemMenu> findPageList(SystemMenu row) {
        return systemMenuMapper.findPageList(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return int 返回分页总数
     * @title 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemMenu row) {
        return systemMenuMapper.findPageListCount(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @title 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemMenu> findByList(SystemMenu row) {
        return systemMenuMapper.findByList(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @title 根据条件查询初始化集合
     * @description 根据条件查询初始化集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemMenu> findByInitList(SystemMenu row) {
        return systemMenuMapper.findByInitList(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return java.util.List<com.framework.model.system.SystemMenu>
     * @title 公共根据条件查询树形集合
     * @description 公共根据条件查询树形集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    public List<SystemMenu> findByTreeList(SystemMenu row) {
        return systemMenuMapper.findByTreeList(row);
    }

    /**
     * @param row 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @title 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemMenu row) {
        ResponseResult r = getResponseResult();
        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE && MenuTargetEnum.getCodeIsCache(row.getTarget())) {
            return r.fail().setMsg("菜单请选择打开方式：不为提交事件!");
        }
        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE && !MenuTargetEnum.getCodeIsCache(row.getTarget())) {
            return r.fail().setMsg("按钮请选择打开方式：提交事件!");
        }
        int isExist = systemMenuMapper.isExist(row);
        if (isExist > NumeralUtil.POSITIVE_ZERO) {
            return r.failRepeat().setMsg("code" + ResultEnum.CODE3000.getMsg());
        }
        if (StringUtils.isNotEmpty(row.getUrl())) {
            int isExistUrl = systemMenuMapper.isExistUrl(row);
            if (isExistUrl > NumeralUtil.POSITIVE_ZERO) {
                return r.failRepeat().setMsg("url" + ResultEnum.CODE3000.getMsg());
            }
        }
        if (row.getParentId() != null && row.getParentId().longValue() > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
            SystemMenu sm = systemMenuMapper.selectByPrimaryKey(row.getParentId());
            if (sm == null) {
                return r.fail();
            }
            if (sm.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
                return r.fail().setMsg("非菜单禁止添加子项!");
            }
            row.setLevel(sm.getLevel() + 1);
        } else {
            row.setLevel(NumeralUtil.POSITIVE_ZERO);
            row.setParentId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterTime(date);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        if (StringUtils.isEmpty(row.getTarget())) {
            row.setTarget(MenuUtil.MENU_STR_SELF);
        }
        systemMenuMapper.insertSelective(row);
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setIsEnable(row.getIsEnable());
        systemMenu.setCode(row.getCode());
        systemMenu.setIcon(row.getIcon());
        systemMenu.setId(row.getId());
        systemMenu.setName(row.getName());
        systemMenu.setParentId(row.getParentId());
        systemMenu.setTarget(row.getTarget());
        systemMenu.setUrl(row.getUrl());
        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
            super.redisUtil.setAuthButtonString(row.getCode(), JSONObject.toJSONString(systemMenu));
        }
//        else if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//            super.redisUtil.setAuthMenuString(row.getCode(), JSONObject.toJSONString(systemMenu));
//        }
        return r.success();
    }

    /**
     * @param row 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @title 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemMenu row) {
        ResponseResult r = getResponseResult();
        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE && MenuTargetEnum.getCodeIsCache(row.getTarget())) {
            return r.fail().setMsg("菜单请选择打开方式：不为提交事件!");
        }
        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE && !MenuTargetEnum.getCodeIsCache(row.getTarget())) {
            return r.fail().setMsg("按钮请选择打开方式：提交事件!");
        }
        int isExist = systemMenuMapper.isExist(row);
        if (isExist > NumeralUtil.POSITIVE_ZERO) {
            return r.failRepeat().setMsg("code" + ResultEnum.CODE3000.getMsg());
        }
        if (StringUtils.isNotEmpty(row.getUrl())) {
            int isExistUrl = systemMenuMapper.isExistUrl(row);
            if (isExistUrl > NumeralUtil.POSITIVE_ZERO) {
                return r.failRepeat().setMsg("url" + ResultEnum.CODE3000.getMsg());
            }
        }
//        int num = systemMenuMapper.isExist(row);
//        if (num > NumeralUtil.POSITIVE_ZERO) {
//            return r.failRepeat();
//        }
        row.setCode(null);
        SystemMenu sm = systemMenuMapper.selectByPrimaryKey(row.getId());
        if (sm == null) {
            return r.fail().setMsg("修改信息不存在!");
        }
        if (row.getCategory().intValue() != sm.getCategory().intValue()) {
            //按钮转菜单
            if (row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
                SystemMenu systemMenu = new SystemMenu();
                systemMenu.setParentId(row.getId());
                systemMenu.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
                int count = systemMenuMapper.findPageListCount(systemMenu);
                if (count > NumeralUtil.POSITIVE_ZERO) {
                    return super.getResponseResult().fail().setMsg("有子项信息，禁止转为按钮!");
                }
            }
//            //菜单转按钮
//            else if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//
//            }
        }

        row.setLevel(null);
        row.setParentId(null);
        Long userId = getUserId();
        Date date = new Date();
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        systemMenuMapper.updateByPrimaryKeySelective(row);
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setIsEnable(row.getIsEnable());
        systemMenu.setCode(sm.getCode());
        systemMenu.setIcon(row.getIcon());
        systemMenu.setId(row.getId());
        systemMenu.setName(row.getName());
        systemMenu.setParentId(row.getParentId());
        systemMenu.setTarget(row.getTarget());
        systemMenu.setUrl(row.getUrl());
        List<SystemRoleMenu> rmList = systemRoleMenuMapper.findByMenuIdListRoleCode(sm.getId());
        SystemMenu menu = systemMenuMapper.selectByPrimaryKey(sm.getParentId());
        if (rmList != null && rmList.size() > NumeralUtil.POSITIVE_ZERO) {
            for (SystemRoleMenu srm : rmList) {
                String urlKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL,
                        RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL, srm.getRoleCode());
                String obj = super.redisUtil.getAuthRoleString(srm.getRoleCode());
                if (StringUtils.isNotEmpty(obj)) {
                    SystemRole redisSR = JSONObject.parseObject(obj, SystemRole.class);
                    boolean isSR =
                            redisSR.getMenuCodeList() != null
                                    && redisSR.getMenuCodeList().size() > NumeralUtil.POSITIVE_ZERO
                                    && redisSR.getMenuCodeList().remove(sm.getCode());
                    if (isSR || row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
                        if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//                            redisSR.getMenuCodeList().add(row.getCode());
                            redisSR.getMenuCodeList().add(sm.getCode());
                        }
                        super.redisUtil.deleteKey(RedisKeyUtil.getPermissionRoleKey(redisSR.getRoleCode()));
                        super.redisUtil.setAuthRoleString(redisSR.getRoleCode(), JSONObject.toJSONString(redisSR));
                    }
                }
                //处理菜单转按钮
                if (menu != null
                        && row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
                    String oldRoleMenuCode = srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menu.getCode();
                    String menuStr = super.redisUtil.getAuthMenuString(oldRoleMenuCode);
                    if (StringUtils.isNotEmpty(menuStr)) {
                        SystemMenu oldMenu = JSONObject.parseObject(menuStr, SystemMenu.class);
                        if (oldMenu.getButtonCodeList() == null) {
                            oldMenu.setButtonCodeList(new ArrayList<String>());
                        }
                        oldMenu.getButtonCodeList().remove(sm.getCode());
//                        oldMenu.getButtonCodeList().add(row.getCode());
                        oldMenu.getButtonCodeList().add(sm.getCode());
                        super.redisUtil.deleteKey(RedisKeyUtil.getPermissionMenuKey(srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menu.getCode()));
                        super.redisUtil.setAuthMenuString(oldRoleMenuCode, JSONObject.toJSONString(oldMenu));
                    }
                    //清除已关联权限的按钮URL
                    if (StringUtils.isNotEmpty(sm.getUrl())) {
                        super.redisUtil.deleteListIndex(urlKey, NumeralUtil.POSITIVE_ZERO, sm.getUrl());
                    }
                    if (StringUtils.isNotEmpty(row.getUrl()) && row.getIsEnable().intValue() == NumeralUtil.POSITIVE_ONE) {
                        super.redisUtil.setList(urlKey, row.getUrl());
                    }
                    //修改前的数据为菜单
                    if (sm.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
                        String oldRoleMenuCode1 = RedisKeyUtil.getPermissionMenuKey(srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode());
                        super.redisUtil.deleteKey(oldRoleMenuCode1);
                    }
                    continue;
                }
                //处理按钮转菜单
                if (menu != null
                        && row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
                    String oldRoleMenuCode = srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menu.getCode();
//                      String oldRoleMenuCode = RedisKeyUtil.getPermissionMenuKey(srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menu.getCode());
                    String menuStr = super.redisUtil.getAuthMenuString(oldRoleMenuCode);
                    if (StringUtils.isNotEmpty(menuStr)) {
                        SystemMenu oldMenu = JSONObject.parseObject(menuStr, SystemMenu.class);
                        boolean isSR =
                                oldMenu.getButtonCodeList() != null
                                        && oldMenu.getButtonCodeList().size() > NumeralUtil.POSITIVE_ZERO
                                        && oldMenu.getButtonCodeList().remove(sm.getCode());
                        if (isSR) {
                            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionMenuKey(srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menu.getCode()));
                            super.redisUtil.setAuthMenuString(oldRoleMenuCode, JSONObject.toJSONString(oldMenu));
                        }
                    }
                    //清除已关联权限的按钮URL
                    if (StringUtils.isNotEmpty(sm.getUrl())) {
                        super.redisUtil.deleteListIndex(urlKey, NumeralUtil.POSITIVE_ZERO, sm.getUrl());
                    }
                    //修改前的数据为菜单
                    if (sm.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
                        String oldRoleMenuCode1 = RedisKeyUtil.getPermissionMenuKey(srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode());
                        super.redisUtil.deleteKey(oldRoleMenuCode1);
                        if (StringUtils.isNotEmpty(row.getUrl())) {
                            super.redisUtil.setList(urlKey, row.getUrl());
                        }
                    }
                    //修改后的数据为菜单
                    if (row.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//                        String newRoleMenuCode = srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + row.getCode();
                        String newRoleMenuCode = srm.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode();
                        super.redisUtil.setAuthMenuString(newRoleMenuCode, JSONObject.toJSONString(systemMenu));
                    }
                }
            }
        }
        if (sm.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
//            if (row.getCategory().intValue() != sm.getCategory().intValue() || !row.getCode().equals(sm.getCode())) {
            if (row.getCategory().intValue() != sm.getCategory().intValue()) {
                String oldMenuButtonCode = menu.getCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode();
                super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(oldMenuButtonCode));
            }
        }
        if (menu != null && row.getCategory().intValue() == NumeralUtil.POSITIVE_ONE) {
//            String newMenuButtonCode = menu.getCode() + SymbolUtil.NO_INPUT_METHOD_COLON + row.getCode();
//            String newMenuButtonCode = menu.getCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode();
            String newMenuButtonCode = menu.getCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getUrl();
            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(newMenuButtonCode));
//            if(systemMenu.getIsEnable().intValue() == NumeralUtil.POSITIVE_ONE){
//            }
            super.redisUtil.setAuthButtonString(newMenuButtonCode, JSONObject.toJSONString(systemMenu));
        }
        return r.success();
    }

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @title 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        //验证角色关联菜单是否存在
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setMenuId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        systemRoleMenu.setIdList(idList);
        List<SystemRoleMenu> srbList = systemRoleMenuMapper.findByIsExistRoleIdList(systemRoleMenu);
        if (srbList != null && srbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除关联信息:");
            for (SystemRoleMenu srm : srbList) {
                if (sb.length() > NumeralUtil.POSITIVE_TEN) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(srm.getRoleName());
                } else {
                    sb.append(srm.getRoleName());
                }
            }
            return r.fail().setMsg(sb.toString());
        }
        SystemMenu row = new SystemMenu();
        row.setIdList(idList);
        List<SystemMenu> menuList = systemMenuMapper.findByList(row);
//        String[] delKey = new String[menuList.size()];
        List<String> delKeyList = new ArrayList<String>(menuList.size() * NumeralUtil.POSITIVE_TWO);
        for (SystemMenu item : menuList) {
            if (item.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//                List<SystemRoleMenu> rmList = systemRoleMenuServiceImpl.findByMenuIdListRoleCode(item.getId());
//                for (SystemRoleMenu sr : rmList) {
//                    String roleMenuCode = RedisKeyUtil.getPermissionMenuKey(sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + item.getCode());
//                    delKeyList.add(roleMenuCode);
//                }
                delKeyList.add(item.getCode());
            } else {
                String roleMenuCode = RedisKeyUtil.getPermissionButtonKey(item.getCode());
                delKeyList.add(roleMenuCode);
            }
        }
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        row.setOperaterId(userId);
        systemMenuMapper.deleteList(row);
        if (delKeyList != null && delKeyList.size() > NumeralUtil.POSITIVE_ZERO) {
            super.redisUtil.deleteKey(delKeyList);
        }
        return r.success();
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @title 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id) {
        ResponseResult r = getResponseResult();
        //验证角色关联菜单是否存在
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setMenuId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        systemRoleMenu.setIdList(Arrays.asList(id));
        List<SystemRoleMenu> srbList = systemRoleMenuMapper.findByIsExistRoleIdList(systemRoleMenu);
        if (srbList != null && srbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除关联信息:");
            for (SystemRoleMenu srm : srbList) {
                if (sb.length() > NumeralUtil.POSITIVE_TEN) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(srm.getRoleName());
                } else {
                    sb.append(srm.getRoleName());
                }
            }
            return r.fail().setMsg(sb.toString());
        }
        SystemMenu row = new SystemMenu();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        systemMenuMapper.delete(row);
        if (systemMenu.getCategory().intValue() == NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE) {
//            List<SystemRoleMenu> rmList = systemRoleMenuServiceImpl.findByMenuIdListRoleCode(systemMenu.getId());
//            for (SystemRoleMenu sr : rmList) {
//                String roleMenuCode = RedisKeyUtil.getPermissionMenuKey(sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + systemMenu.getCode());
//                super.redisUtil.deleteKey(RedisKeyUtil.getPermissionMenuKey(roleMenuCode));
//            }
            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionMenuKey(systemMenu.getCode()));
        } else {
            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(systemMenu.getCode()));
        }
        return r.success();
    }

    /**
     * @param id   1 编号
     * @param code 2 菜单代码
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String code) {
        SystemMenu param = new SystemMenu();
        param.setId(id);
        param.setCode(code);
        Boolean is = systemMenuMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemMenu
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemMenu getByIdParam(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }


    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询菜单列表
     * @description 本类后台管理根据条件分页查询菜单列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemMenu param) {
        ResponseResult r = getResponseResult();
        if (param == null || param.getParentId() == null) {
            return r.success().setCount(NumeralUtil.POSITIVE_ZERO);
        }
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemMenuMapper.findPageListCount(param);
        List<SystemMenu> list = systemMenuMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    //组装树形集合
    private List<Tree> childrenList(SystemMenu m, List<SystemMenu> list) {
        Iterator<SystemMenu> it = list.iterator();
        List<Tree> treeList = new ArrayList<Tree>();
        while (it.hasNext()) {
            SystemMenu sm = it.next();
            if (sm.getParentId() != null && m.getId().longValue() == sm.getParentId().longValue()) {
                Tree z = new Tree();
                z.setId(sm.getId().toString());
                z.setTitle(sm.getName());
                //一定要在删除后在执行递归，不然会出现并发修改操作BUG
//                it.remove();
                z.setChildren(childrenList(sm, list));
                treeList.add(z);
            }
        }
        return treeList;
    }

    /**
     * @param id      编号
     * @param pId     上级编号
     * @param keyword 搜索菜单名称
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询树形菜单集合
     * @description 根据条件查询树形菜单集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:52
     */
    @Override
    public ResponseResult findMenuTreeList(Long id, Long pId, String keyword) {
        SystemMenu param = new SystemMenu();
        param.setCategory(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        if (StringUtils.isNotEmpty(keyword)) {
            param.setKeyword(keyword);
        } else {
            param.setParentId(id);
        }
        List<SystemMenu> list = systemMenuMapper.findByList(param);
        if (list == null || list.size() == NumeralUtil.POSITIVE_ZERO) {
            return super.getResponseResult().fail();
        }
        Iterator<SystemMenu> it = list.iterator();
        List<SystemMenu> rootList = new ArrayList<SystemMenu>();
        while (it.hasNext()) {
            SystemMenu m = it.next();
            if (m.getParentId() == null || m.getParentId().longValue() < NumeralUtil.POSITIVE_ONE) {
                rootList.add(m);
                it.remove();
            }
        }

        List<Tree> treeList = new ArrayList<Tree>();
        Tree ztree = new Tree();
        ztree.setId(String.valueOf(NumeralUtil.POSITIVE_ZERO));
        ztree.setTitle(TreeUtil.TREE_ROOT_NODE_NAME);
//        ztree.setDisabled(Boolean.TRUE);
        ztree.setSpread(Boolean.TRUE);
//        ztree.setChecked(Boolean.TRUE);
        treeList.add(ztree);

        for (SystemMenu m : rootList) {
            Tree z = new Tree();
            z.setId(m.getId().toString());
            z.setTitle(m.getName());
            z.setChildren(childrenList(m, list));
//            treeList.add(z);
            ztree.getChildren().add(z);
        }
        return super.getResponseResult().success().setData(treeList);
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Title 根据编号查询信息
     * @Description 根据编号查询信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    @Override
    public ResponseResult getByIdInfo(Long id) {
        if (id.longValue() == NumeralUtil.POSITIVE_ZERO) {
            SystemMenu param = new SystemMenu();
            param.setId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
            param.setName(TreeUtil.TREE_ROOT_NODE_NAME);
            return super.getResponseResult().success().setData(param);
        }
        SystemMenu param = systemMenuMapper.selectByPrimaryKey(id);
        return super.getResponseResult().success().setData(param);
    }
}
