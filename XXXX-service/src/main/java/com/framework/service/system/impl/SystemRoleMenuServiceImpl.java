package com.framework.service.system.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.model.Tree;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.TreeUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisPrefixUtil;
import com.framework.mapper.system.SystemRoleMenuMapper;
import com.framework.model.system.SystemMenu;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemRoleMenu;
import com.framework.model.system.vo.SystemRoleMenuVo;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemMenuService;
import com.framework.service.system.SystemRoleMenuService;
import com.framework.service.system.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 角色菜单关联业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemRoleMenuServiceImpl")
public class SystemRoleMenuServiceImpl extends BaseService implements SystemRoleMenuService {
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private SystemMenuService systemMenuServiceImpl;

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据角色编号和菜单编号集合物理批量删除
     * @description 公共根据角色编号和菜单编号集合物理批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:07
     */
    @Override
    public int deleteList(SystemRoleMenu row) {
        return systemRoleMenuMapper.deleteList(row);
    }

    /**
     * @param roleId 1 角色编号
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据角色编号物理删除关联数据
     * @description 公共根据角色编号物理删除关联数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:10
     */
    @Override
    public int deleteRoleId(Long roleId) {
        return systemRoleMenuMapper.deleteRoleId(roleId);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共添加
     * @description 公共添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemRoleMenu row) {
        return systemRoleMenuMapper.insert(row);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemRoleMenu row) {
        return systemRoleMenuMapper.insertSelective(row);
    }

    /**
     * @param list 1 角色菜单关联批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemRoleMenu> list) {
        return systemRoleMenuMapper.insertList(list);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<java.lang.Long>
     * @titel 公共根据条件查询菜单编号集合
     * @description 公共根据条件查询菜单编号集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:19
     */
    public List<Long> findByMenuIdList(SystemRoleMenu row) {
        return systemRoleMenuMapper.findByMenuIdList(row);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据条件查询存在集合
     * @description 公共根据条件查询存在集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    @Override
    public List<SystemRoleMenu> findByIsExistList(SystemRoleMenu row) {
        return systemRoleMenuMapper.findByIsExistList(row);
    }

    /**
     * @param roleId 1 角色编号
     * @return java.util.List<com.framework.model.system.SystemRoleMenu> 返回集合
     * @titel 公共根据角色编号查询菜单代码集合
     * @description 公共根据角色编号查询菜单代码集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public List<SystemRoleMenu> findByRoleIdListMenuCode(Long roleId) {
        return systemRoleMenuMapper.findByRoleIdListMenuCode(roleId);
    }

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.system.SystemRoleMenu> 返回集合
     * @titel 公共根据菜单编号查询角色代码集合
     * @description 公共根据菜单编号查询角色代码集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public List<SystemRoleMenu> findByMenuIdListRoleCode(Long menuId) {
        return systemRoleMenuMapper.findByMenuIdListRoleCode(menuId);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    @Override
    public List<SystemRoleMenu> findByList(SystemRoleMenu row) {
        return systemRoleMenuMapper.findByList(row);
    }

    /**
     * @param row 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.system.SystemRoleMenu>
     * @titel 初始化查询
     * @description 初始化查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:20
     */
    @Override
    public List<SystemRoleMenu> findByInitList(SystemRoleMenu row) {
        return systemRoleMenuMapper.findByInitList(row);
    }

    /**
     * @param param 1 对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:23
     */
    @Override
    public ResponseResult save(SystemRoleMenuVo param) {
        ResponseResult r = getResponseResult();
        SystemRole sr = systemRoleServiceImpl.selectByPrimaryKey(param.getRoleId());
        if (sr == null) {
            return r.fail().setMsg("角色不存在!");
        }
        if (param.getMenuIdList() == null || param.getMenuIdList().size() == NumeralUtil.POSITIVE_ZERO) {
            systemRoleMenuMapper.deleteRoleId(param.getRoleId());
            redisUtil.setAuthRoleString(sr.getRoleCode(), JSONObject.toJSONString(sr));
            return r.success();
        }
        param.getMenuIdList().remove(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
        List<SystemRoleMenu> addList = new ArrayList<SystemRoleMenu>();
        for (Long menuId : param.getMenuIdList()) {
            SystemRoleMenu srm = new SystemRoleMenu();
            srm.setRoleId(param.getRoleId());
            srm.setMenuId(menuId);
            addList.add(srm);
        }
        systemRoleMenuMapper.deleteRoleId(param.getRoleId());
        systemRoleMenuMapper.insertList(addList);
        //查询按钮
        SystemMenu button = new SystemMenu();
        button.setIsEnable(NumeralUtil.POSITIVE_ONE);
        button.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        button.setCategory(NumeralUtil.POSITIVE_ONE);
        button.setType(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        button.setIdList(param.getMenuIdList());
        List<SystemMenu> buttonList = systemMenuServiceImpl.findByInitList(button);

        //查询菜单
        SystemMenu menu = new SystemMenu();
        menu.setIsEnable(NumeralUtil.POSITIVE_ONE);
        menu.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        menu.setCategory(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        menu.setType(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        menu.setIdList(param.getMenuIdList());
        List<SystemMenu> menuList = systemMenuServiceImpl.findByInitList(menu);

        String roleCodeKey = RedisKeyUtil.getPermissionMenuKey(sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + "*");


        //处理菜单关联的按钮
//        SystemButton systemButton = new SystemButton();
//        systemButton.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
//        List<SystemButton> buttonList = systemButtonServiceImpl.findByList(systemButton);
//        SystemRoleMenuButton srmb = new SystemRoleMenuButton();
//        srmb.setRoleId(sr.getId());
//        List<SystemRoleMenuButton> roleMenuButtonList = systemRoleMenuButtonServiceImpl.findByList(srmb);
//        Map<String, List<String>> rsmbMap = new HashMap<String, List<String>>();
//        for (SystemRoleMenuButton rsmb : roleMenuButtonList) {//循环菜单
//            String roleMenuIdCode = rsmb.getRoleId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + rsmb.getMenuId();
//            List<String> list = rsmbMap.get(roleMenuIdCode);//判断当前map集合中的按钮代码集合
//            if (list == null) {//按钮代码集合不存在就是第一次循环。
//                list = new ArrayList<String>();//创建一个新的集合
//            }
//            for (SystemButton sb : buttonList) {//循环按钮
//                //匹配对应的菜单按钮关联ID
//                if (sb.getId() == rsmb.getButtonId() || rsmb.getButtonId().equals(sb.getId())) {
//                    //匹配上了以后把按钮代码存入list集合中
//                    list.add(sb.getButtonCode());
//                    break;
//                }
//            }
//            //保存对应的菜单ID键，按钮代码集合
//            rsmbMap.put(roleMenuIdCode, list);
//        }
        String urlKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL,
                RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL, sr.getRoleCode());
        Set<String> roleCodeKeys = redisUtil.getKeys(roleCodeKey);
        Set<String> urlKeys = redisUtil.getKeys(urlKey);
        if (roleCodeKeys != null && roleCodeKeys.size() > NumeralUtil.POSITIVE_ZERO) {
            redisUtil.deleteKey(roleCodeKeys);
        }
        if (urlKeys != null && urlKeys.size() > NumeralUtil.POSITIVE_ZERO) {
            redisUtil.deleteKey(urlKeys);
        }
        List<String> menuCodeList = new ArrayList<String>();
        List<String> buttonCodeList = null;
        List<String> buttonUrlList = new ArrayList<String>(buttonList.size());
        for (SystemMenu menuItem : menuList) {
            if (!param.getMenuIdList().contains(menuItem.getId())) {
                continue;
            }
            buttonCodeList = new ArrayList<String>(NumeralUtil.POSITIVE_THIRTY_TWO);
            Iterator<SystemMenu> it = buttonList.iterator();
            while (it.hasNext()) {
                //处理按钮权限，
                SystemMenu sb = it.next();
                if (!param.getMenuIdList().contains(sb.getId())) {
                    continue;
                }
                //判断菜单和按钮关联
                if (sb.getParentId().longValue() == menuItem.getId()) {
                    buttonCodeList.add(sb.getCode());
                    buttonUrlList.add(sb.getUrl());
//                    redisUtil.setAuthButtonString(sb.getCode(), JSONObject.toJSONString(sb));
//                    redisUtil.setAuthButtonString(sb.getUrl(), JSONObject.toJSONString(sb));
                    it.remove();
                }
            }
            menuCodeList.add(menuItem.getCode());
            if (buttonCodeList.size() > NumeralUtil.POSITIVE_ZERO) {
                menuItem.setButtonCodeList(buttonCodeList);
            }
            //菜单缓存到redis
            String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + menuItem.getCode();
            redisUtil.setAuthMenuString(roleMenuCode, JSONObject.toJSONString(menuItem));
        }
        if (buttonUrlList.size() > NumeralUtil.POSITIVE_ZERO) {
            redisUtil.setListAll(urlKey, buttonUrlList, NumeralUtil.POSITIVE_ZERO);
        }
//        for (SystemMenu sm : param.getMenuIdList()) {
//            if (param.getMenuIdList().contains(sm.getId())) {
//                String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode();
////                String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getUrl();
//                menuCodeList.add(sm.getCode());
////                String roleMenuIdCode = sr.getId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + sm.getId();
////                List<String> smList = rsmbMap.get(roleMenuIdCode);
////                sm.setButtonCodeList(smList);
//                redisUtil.setAuthMenuString(roleMenuCode, JSONObject.toJSONString(sm));
//            }
//        }


        //更新Redis缓存
        sr.setMenuCodeList(menuCodeList);
        redisUtil.setAuthRoleString(sr.getRoleCode(), JSONObject.toJSONString(sr));
        return r.success();
    }

    /**
     * @param param 1 角色菜单关联实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询数据集合
     * @description 根据条件查询数据集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:23
     */
    @Override
    public ResponseResult findByParamList(SystemRoleMenu param) {
        ResponseResult r = getResponseResult();
        List<SystemRoleMenu> list = systemRoleMenuMapper.findByList(param);
        return r.success().setData(list);
    }

    //递归处理树形
    private List<Tree> childrenList(SystemMenu m, List<SystemMenu> list, List<Long> lList) {
        Iterator<SystemMenu> it = list.iterator();
        List<Tree> treeList = new ArrayList<Tree>();
        while (it.hasNext()) {
            SystemMenu sm = it.next();
            if (sm.getParentId() != null && m.getId().longValue() == sm.getParentId().longValue()) {
                Tree z = new Tree();
                z.setId(sm.getId().toString());
                z.setTitle(sm.getName());
                z.setSpread(Boolean.TRUE && lList != null);
//                it.remove();
                z.setChildren(childrenList(sm, list, lList));
                if (z.getChildren().size() == NumeralUtil.POSITIVE_ZERO
                        && lList != null
                        && lList.contains(sm.getId())) {
                    z.setChecked(Boolean.TRUE);
                }
                treeList.add(z);
            }
        }
        return treeList;
    }

    /**
     * @param roleId  1 角色编号
     * @param id      2 菜单编号
     * @param pId     3 菜单上级编号
     * @param keyword 4 搜索条件
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询树形菜单集合
     * @description 根据条件查询树形菜单集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 18:25
     */
    @Override
    public ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword) {
        SystemMenu param = new SystemMenu();
        if (StringUtils.isNotEmpty(keyword)) {
            param.setKeyword(keyword);
        } else {
            param.setParentId(id);
        }
        List<SystemMenu> list = systemMenuServiceImpl.findByList(param);
        if (list == null || list.size() == NumeralUtil.POSITIVE_ZERO) {
            return super.getResponseResult().fail();
        }
        List<Long> lList = null;
        if (roleId != null && roleId.longValue() > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(roleId);
            lList = systemRoleMenuMapper.findByMenuIdList(systemRoleMenu);
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
//        if (rootList.size() == NumeralUtil.POSITIVE_ZERO
//                && lList != null
//                && lList.size() > NumeralUtil.POSITIVE_ZERO) {
//            ztree.setChecked(Boolean.TRUE);
//        }
        ztree.setSpread(Boolean.TRUE);
        for (SystemMenu m : rootList) {
            Tree z = new Tree();
            z.setId(m.getId().toString());
            z.setTitle(m.getName());
            z.setSpread(Boolean.TRUE);
            z.setChildren(childrenList(m, list, lList));
            if (z.getChildren().size() == NumeralUtil.POSITIVE_ZERO
                    && lList != null
                    && lList.contains(m.getId())) {
                z.setChecked(Boolean.TRUE);
            }
            ztree.getChildren().add(z);
        }
        treeList.add(ztree);
        return super.getResponseResult().success().setData(treeList);
    }

    /**
     * @param roleId 1 角色编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据编号查询关联信息
     * @Description 根据编号查询关联信息
     * @Author 龘鵺
     * @DateTime 23/5/2023 下午6:30
     */
    @Override
    public ResponseResult findRoleMenuList(Long roleId) {
        SystemRoleMenu param = new SystemRoleMenu();
        param.setRoleId(roleId);
        List<SystemRoleMenu> systemRoleMenuList = systemRoleMenuMapper.findByList(param);
        return super.getResponseResult().success().setData(systemRoleMenuList);
    }
}
