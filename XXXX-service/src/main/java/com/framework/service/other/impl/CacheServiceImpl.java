package com.framework.service.other.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.enums.menu.MenuTargetEnum;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisPrefixUtil;
import com.framework.model.system.SystemDict;
import com.framework.model.system.SystemMenu;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemRoleMenu;
import com.framework.model.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.other.CacheService;
import com.framework.service.system.SystemDictService;
import com.framework.service.system.SystemMenuService;
import com.framework.service.system.SystemRoleMenuService;
import com.framework.service.system.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.other.impl
 * @description 缓存业务接口实现类
 * @date 2022/5/13 9:20
 */
@Service("cacheServiceImpl")
public class CacheServiceImpl extends BaseService implements CacheService {
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private SystemMenuService systemMenuServiceImpl;
    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;
    @Autowired
    private SystemDictService systemDictServiceImpl;

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 刷新权限缓存
     * @description 刷新权限缓存
     * @author 龘鵺
     * @datetime 2022/5/13 9:54
     */
    @Override
    public ResponseResult refreshCache() {
        SystemUser systemUser = getUser();
        if (!super.ignoredRoleAuth.getAuthList().contains(systemUser.getRoleCode())) {
            return super.getResponseResult().fail().setMsg("无权限!");
        }
        initCache();
        return super.getResponseResult().success();
    }

    /**
     * @titel 初始化权限缓存
     * @description 初始化权限缓存
     * @author 龘鵺
     * @datetime 2022/5/13 9:53
     */
    @Override
    public void initCache() {
        //清楚权限缓存开始
        String fuzzyKeyRole = RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME + SymbolUtil.NO_INPUT_METHOD_COLON + RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE + SymbolUtil.NO_INPUT_METHOD_COLON + SymbolUtil.NO_INPUT_METHOD_ASTERISK;
        String fuzzyKeyMenu = RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME + SymbolUtil.NO_INPUT_METHOD_COLON + RedisPrefixUtil.PREFIX_STR_PERMISSION_MENU + SymbolUtil.NO_INPUT_METHOD_COLON + SymbolUtil.NO_INPUT_METHOD_ASTERISK;
        String fuzzyKeyButton = RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME + SymbolUtil.NO_INPUT_METHOD_COLON + RedisPrefixUtil.PREFIX_STR_PERMISSION_BUTTON + SymbolUtil.NO_INPUT_METHOD_COLON + SymbolUtil.NO_INPUT_METHOD_ASTERISK;
        String fuzzyKeyButtonUrl =
                RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME + SymbolUtil.NO_INPUT_METHOD_COLON + RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL + SymbolUtil.NO_INPUT_METHOD_COLON + SymbolUtil.NO_INPUT_METHOD_ASTERISK;
        Set<String> keysSetRole = redisUtil.getKeys(fuzzyKeyRole);
        Set<String> keysSetMenu = redisUtil.getKeys(fuzzyKeyMenu);
        Set<String> keysSetButton = redisUtil.getKeys(fuzzyKeyButton);
        Set<String> keysSetButtonUrl = redisUtil.getKeys(fuzzyKeyButtonUrl);
        if (keysSetRole != null) {
            redisUtil.deleteKey(keysSetRole);
        }
        if (keysSetMenu != null) {
            redisUtil.deleteKey(keysSetMenu);
        }
        if (keysSetButton != null) {
            redisUtil.deleteKey(keysSetButton);
        }
        if (keysSetButtonUrl != null) {
            redisUtil.deleteKey(keysSetButtonUrl);
        }
        //清楚权限缓存结束
//        SystemButton systemButton = new SystemButton();
//        systemButton.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
//        List<SystemButton> buttonList = systemButtonServiceImpl.findByList(systemButton);

        SystemMenu button = new SystemMenu();
        button.setIsEnable(NumeralUtil.POSITIVE_ONE);
        button.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        button.setCategory(NumeralUtil.POSITIVE_ONE);
        button.setType(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        List<SystemMenu> buttonList = systemMenuServiceImpl.findByInitList(button);

        //查询菜单
        SystemMenu menu = new SystemMenu();
        menu.setIsEnable(NumeralUtil.POSITIVE_ONE);
        menu.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        menu.setCategory(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        menu.setType(NumeralUtil.POSITIVE_NINE_THOUSAND_NINE_HUNDRED_AND_NINETY_NINE);
        List<SystemMenu> menuList = systemMenuServiceImpl.findByInitList(menu);

        SystemRole systemRole = new SystemRole();
        systemRole.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemRole> roleList = systemRoleServiceImpl.findByInitList(systemRole);

        List<SystemRoleMenu> roleMenuList = systemRoleMenuServiceImpl.findByInitList(null);
//        List<SystemRoleMenuButton> roleMenuButtonList = systemRoleMenuButtonServiceImpl.findByList(null);

        SystemDict systemDict = new SystemDict();
        systemDict.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemDict> sdList = systemDictServiceImpl.findByInitList(systemDict);


        //处理角色关联的菜单
        Map<Long, List<String>> srmMap = new HashMap<Long, List<String>>();
        Map<Long, List<SystemMenu>> srmMapList = new HashMap<Long, List<SystemMenu>>();
        Map<String, List<String>> srmbMap = new HashMap<String, List<String>>();
        Map<String, List<String>> srmbUrlMap = new HashMap<String, List<String>>();
        for (SystemRoleMenu srm : roleMenuList) {//循环角色
            List<String> list = srmMap.get(srm.getRoleId());//判断当前map集合中的菜单代码集合
            List<SystemMenu> systemMenuList = srmMapList.get(srm.getRoleId());//判断当前map集合中的菜单代码集合

            if (list == null) {//菜单代码集合不存在就是第一次循环。
                list = new ArrayList<String>(menuList.size());//创建一个新的集合
            }
            if (systemMenuList == null) {//菜单集合不存在就是第一次循环。
                systemMenuList = new ArrayList<SystemMenu>(menuList.size());//创建一个新的集合
            }

            for (SystemMenu sm : menuList) {//循环菜单
                if (MenuTargetEnum.getCodeIsCache(sm.getTarget())) {
                    continue;
                }
                //匹配对应的角色菜单关联ID
                if (sm.getId().longValue() == srm.getMenuId().longValue() || srm.getMenuId().equals(sm.getId())) {
                    //匹配上了以后把菜单代码存入list集合中
                    list.add(sm.getCode());
                    systemMenuList.add(sm);
                }
            }
            for (SystemMenu sb : buttonList) {//循环按钮
                //匹配对应的角色菜单关联ID
                if (sb.getParentId().longValue() == srm.getMenuId().longValue()) {
                    String menuButtonCode = srm.getRoleId() + "_" + sb.getParentId();
                    List<String> systemMenuButtonList = srmbMap.get(menuButtonCode);//判断当前map集合中的菜单代码集合
                    List<String> urlList = srmbUrlMap.get(srm.getRoleCode());//
                    if (systemMenuButtonList == null) {//按钮集合不存在就是第一次循环。
                        systemMenuButtonList = new ArrayList<String>(NumeralUtil.POSITIVE_THIRTY);//创建一个新的集合
                    }
                    //匹配上了以后把菜单代码存入list集合中
                    systemMenuButtonList.add(sb.getCode());
                    srmbMap.put(menuButtonCode, systemMenuButtonList);
                    if (urlList == null) {//按钮集合不存在就是第一次循环。
                        urlList = new ArrayList<String>(NumeralUtil.POSITIVE_THIRTY);//创建一个新的集合
                    }
                    urlList.add(sb.getUrl());
                    srmbUrlMap.put(srm.getRoleCode(), urlList);
                }
                if (sb.getParentId().longValue() == srm.getMenuId().longValue()) {
//                    String menuButtonCode = srm.getMenuCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sb.getCode();
                    String menuButtonCode = srm.getMenuCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sb.getUrl();
                    redisUtil.setAuthButtonString(menuButtonCode, JSONObject.toJSONString(sb));
//                    redisUtil.setAuthButtonString(sb.getUrl(), JSONObject.toJSONString(sb));
                }
            }
            //保存对应的角色ID键，菜单代码集合
            srmMap.put(srm.getRoleId(), list);
            srmMapList.put(srm.getRoleId(), systemMenuList);
        }

        //处理按钮
//        Map<String, List<String>> smbMap = new HashMap<String, List<String>>();s
//        List<String> buttonCodeList = null;
//        for (SystemMenu menuItem : menuList) {
//            buttonCodeList = new ArrayList<String>(NumeralUtil.POSITIVE_SIXTEEN);
//            Iterator<SystemMenu> it = buttonList.iterator();
//            while (it.hasNext()) {
//                //处理按钮权限，
//                SystemMenu sb = it.next();
//                //判断菜单和按钮关联
//                if (sb.getParentId().longValue() == menuItem.getId()) {
//                    buttonCodeList.add(sb.getCode());
//                    redisUtil.setAuthButtonString(sb.getCode(), JSONObject.toJSONString(sb));
////                    redisUtil.setAuthButtonString(sb.getUrl(), JSONObject.toJSONString(sb));
//                    it.remove();
//                }
//            }
//            if (buttonCodeList.size() > NumeralUtil.POSITIVE_ZERO) {
//                menuItem.setButtonCodeList(buttonCodeList);
//            }
////            //菜单缓存到redis
////            redisUtil.setAuthMenuString(menuItem.getCode(), JSONObject.toJSONString(menuItem));
//        }

        //{"buttonCodeList":["add","edit","del","password","upload","download","batchDel","lock","view","2"]

        if (srmbUrlMap.size() > NumeralUtil.POSITIVE_ZERO) {
            srmbUrlMap.forEach((k, v) -> {
                String urlKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL,
                        RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL, k);
                redisUtil.setListAll(urlKey, v, NumeralUtil.POSITIVE_ZERO);
            });
        }

        //角色缓存到redis
        for (SystemRole sr : roleList) {
            List<String> list = srmMap.get(sr.getId());
            sr.setMenuCodeList(list);
            redisUtil.setAuthRoleString(sr.getRoleCode(), JSONObject.toJSONString(sr));
            List<SystemMenu> systemMenuList = srmMapList.get(sr.getId());
            if (systemMenuList == null) {
                continue;
            }
            //菜单缓存到redis
            for (SystemMenu sm : systemMenuList) {
                String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getCode();
                String menuButtonCode = sr.getId() + "_" + sm.getId();
//                String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getUrl();
                List<String> buttonCodeList = srmbMap.get(menuButtonCode);
//                System.out.println(sr.getRoleCode() + "=" + sm.getCode() + ":" + JSONObject.toJSONString(buttonCodeList));
                sm.setButtonCodeList(buttonCodeList);
                redisUtil.setAuthMenuString(roleMenuCode, JSONObject.toJSONString(sm));
            }
        }
//        //菜单缓存到redis
//        for (SystemMenu sm : menuList) {
//            //按钮缓存到redis
//            for (SystemMenu sb : buttonList) {
//
//            }
//        }

        for (SystemDict sd : sdList) {
            redisUtil.setString(RedisKeyUtil.getPermissionDictKey(sd.getDictKey()), JSONObject.toJSONString(sd));
        }
    }
}
