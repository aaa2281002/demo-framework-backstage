package com.framework.service.service.init.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.system.InitMenuUtil;
import com.framework.model.entity.system.SystemMenu;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.service.init.IndexService;
import com.framework.service.service.system.SystemMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.init.impl
 * @Description 登录后初始化页面业务接口实现类
 * @DateTime 2019/12/25 11:08
 * @Version 1.0
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {
    @Autowired
    private SystemMenuService systemMenuServiceImpl;

    //递归处理子项菜单
    private List getChildMenu(Long pId, List<SystemMenu> list) {
        List<SystemMenu> smList = new ArrayList<SystemMenu>();
        for (SystemMenu sm : list) {
            if (sm.getParentId() == pId) {
                List<SystemMenu> l = getChildMenu(sm.getId(), list);
                sm.setChildMenuList(l);
                smList.add(sm);
            }
        }
        return smList;
    }

    //从redis中获取权限菜单
    private void auth(String roleCode, List<SystemMenu> rootList) {
        if (StringUtils.isEmpty(roleCode)) {
            return;
        }
        Object roleStr = redisUtil.getAuthRoleString(roleCode);
        if (roleStr == null) {
            return;
        }
        SystemRole sr = (SystemRole) roleStr;
        if (sr.getMenuCodeList() == null || sr.getMenuCodeList().size() == 0) {
            return;
        }
        List<SystemMenu> childList = new ArrayList<SystemMenu>();
        for (String menuCode : sr.getMenuCodeList()) {
            String roleMenuCode = roleCode + SymbolUtil.NO_INPUT_METHOD_COLON + menuCode;
            Object menuStr = redisUtil.getAuthMenuString(roleMenuCode);
            if (menuStr != null) {
                SystemMenu sm = (SystemMenu) menuStr;
                if (sm.getIsEnable().intValue() > NumeralUtil.POSITIVE_ONE) {
                    continue;
                }
                if (sm.getParentId() == null || sm.getParentId().intValue() == NumeralUtil.POSITIVE_ZERO) {
                    rootList.add(sm);
                    continue;
                }
                childList.add(sm);
            }
        }
        if (rootList.size() > NumeralUtil.POSITIVE_ZERO) {
            Collections.sort(rootList, new Comparator<SystemMenu>() {
                public int compare(SystemMenu arg0, SystemMenu arg1) {
                    return arg0.getIndexSort().compareTo(arg1.getIndexSort());
                }
            });
            Collections.sort(childList, new Comparator<SystemMenu>() {
                public int compare(SystemMenu arg0, SystemMenu arg1) {
                    return arg0.getIndexSort().compareTo(arg1.getIndexSort());
                }
            });
            combinationMenu(rootList, childList);
        }
    }

    //处理菜单
    private void combinationMenu(List<SystemMenu> rootList, List<SystemMenu> childList) {
        //处理根菜单下所有子菜单
        for (SystemMenu s : rootList) {
            List<SystemMenu> childMenuList = getChildMenu(s.getId(), childList);
            s.setChildMenuList(childMenuList);
//            menuInfoMap.put(s.getMenuCode(), s);
        }
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 登录后初始化菜单
     * @Description 登录后初始化菜单
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/25 11:13
     */
    @Override
    public ResponseResult findByMenuList() {
        ResponseResult r = getResponseResult();
//        Map<String, Object> menuInfoMap = new HashMap<String, Object>();
        SystemUser su = getUser();
        List<SystemMenu> rootList = new ArrayList<SystemMenu>();
        if (super.authList.contains(su.getRoleCode())) {
            //从数据库中查询全部菜单
            SystemMenu sm = new SystemMenu();
            sm.setLtaeNum(NumeralUtil.POSITIVE_ZERO);
            SystemMenu sm2 = new SystemMenu();
            sm2.setGtaeNum(NumeralUtil.POSITIVE_ONE);
            rootList = systemMenuServiceImpl.findByList(sm);//根菜单集合
            List<SystemMenu> childList = systemMenuServiceImpl.findByList(sm2);//子项菜单集合
            if (rootList != null) {
                combinationMenu(rootList, childList);
            }
        } else {
            //从redis根据权限代码获取菜单
            auth(su.getRoleCode(), rootList);
        }
        if (rootList == null) {
            rootList = new ArrayList<SystemMenu>();
        }
        Map<String, Object> objMap = new HashMap<String, Object>();
//        Map<String, Object> clearInfoMap = new HashMap<String, Object>();//清除map
//        clearInfoMap.put(InitMenuUtil.KEY_MENU_CLEAR_URL, InitMenuUtil.VALUE_MENU_CLEAR_URL);
        Map<String, Object> homeInfo = new HashMap<String, Object>();//我的主页参数Map
        homeInfo.put(InitMenuUtil.KEY_MENU_NAME, InitMenuUtil.VALUE_MENU_HOME_NAME);
        homeInfo.put(InitMenuUtil.KEY_MENU_ICON, InitMenuUtil.VALUE_MENU_HOME_ICON);
        homeInfo.put(InitMenuUtil.KEY_MENU_URL_PATH, InitMenuUtil.VALUE_MENU_HOME_URL_PATH);
        Map<String, Object> logoInfoMap = new HashMap<String, Object>();//logo参数map
        logoInfoMap.put(InitMenuUtil.KEY_MENU_NAME, InitMenuUtil.VALUE_MENU_LOGO_NAME);
        logoInfoMap.put(InitMenuUtil.KEY_MENU_IMAGE, InitMenuUtil.VALUE_MENU_LOGO_IMG_PATH);
        logoInfoMap.put(InitMenuUtil.KEY_MENU_URL_PATH, InitMenuUtil.VALUE_MENU_LOGO_URL_PATH);
//        objMap.put(InitMenuUtil.KEY_CLEAR_INFO, clearInfoMap);
        objMap.put(InitMenuUtil.KEY_HOME_INFO, homeInfo);
        objMap.put(InitMenuUtil.KEY_LOGO_INFO, logoInfoMap);
        objMap.put(InitMenuUtil.KEY_MENU_INFO, rootList);
        try {
            return r.ResponseResultSuccess().setData(objMap);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }
}
