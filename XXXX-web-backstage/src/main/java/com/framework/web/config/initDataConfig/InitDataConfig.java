package com.framework.web.config.initDataConfig;

import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.model.entity.system.*;
import com.framework.service.service.system.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initDataConfig
 * @Description 初始化角色，菜单，按钮参数
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
public class InitDataConfig implements CommandLineRunner {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    @Autowired
    private SystemMenuService systemMenuServiceImpl;

    @Autowired
    private SystemButtonService systemButtonServiceImpl;

    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;

    @Autowired
    private SystemRoleMenuButtonService systemRoleMenuButtonServiceImpl;

    @Autowired
    private SystemDictService systemDictServiceImpl;

    /**
     * @param args 1 参数数组
     * @Titel 初始化角色，菜单，按钮到redis中
     * @Description 初始化角色，菜单，按钮到redis中
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:15
     */
    @Override
    public void run(String... args) throws Exception {
        SystemButton systemButton = new SystemButton();
        systemButton.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemButton> buttonList = systemButtonServiceImpl.findByList(systemButton);

        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemMenu> menuList = systemMenuServiceImpl.findByList(systemMenu);

        SystemRole systemRole = new SystemRole();
        systemRole.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemRole> roleList = systemRoleServiceImpl.findByList(systemRole);

        List<SystemRoleMenu> roleMenuList = systemRoleMenuServiceImpl.findByList(null);
        List<SystemRoleMenuButton> roleMenuButtonList = systemRoleMenuButtonServiceImpl.findByList(null);

        SystemDict systemDict = new SystemDict();
        systemDict.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemDict> sdList = systemDictServiceImpl.findByList(systemDict);

        //处理角色关联的菜单
        Map<Long, List<String>> srmMap = new HashMap<Long, List<String>>();
        for (SystemRoleMenu srm : roleMenuList) {//循环角色
            List<String> list = srmMap.get(srm.getRoleId());//判断当前map集合中的菜单代码集合
            if (list == null) {//菜单代码集合不存在就是第一次循环。
                list = new ArrayList<String>();//创建一个新的集合
            }
            for (SystemMenu sm : menuList) {//循环菜单
                //匹配对应的角色菜单关联ID
                if (sm.getId() == srm.getMenuId() || srm.getMenuId().equals(sm.getId())) {
                    //匹配上了以后把菜单代码存入list集合中
                    list.add(sm.getMenuCode());
                    break;
                }
            }
            //保存对应的角色ID键，菜单代码集合
            srmMap.put(srm.getRoleId(), list);
        }

        //处理菜单关联的按钮
        Map<String, List<String>> rsmbMap = new HashMap<String, List<String>>();
        for (SystemRoleMenuButton rsmb : roleMenuButtonList) {//循环菜单
            String roleMenuIdCode = rsmb.getRoleId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + rsmb.getMenuId();
            List<String> list = rsmbMap.get(roleMenuIdCode);//判断当前map集合中的按钮代码集合
            if (list == null) {//按钮代码集合不存在就是第一次循环。
                list = new ArrayList<String>();//创建一个新的集合
            }
            for (SystemButton sb : buttonList) {//循环按钮
                //匹配对应的菜单按钮关联ID
                if (sb.getId() == rsmb.getButtonId() || rsmb.getButtonId().equals(sb.getId())) {
                    //匹配上了以后把按钮代码存入list集合中
                    list.add(sb.getButtonCode());
                    break;
                }
            }
            //保存对应的菜单ID键，按钮代码集合
            rsmbMap.put(roleMenuIdCode, list);
        }
        //角色缓存到redis
        for (SystemRole sr : roleList) {
            List<String> list = srmMap.get(sr.getId());
            sr.setMenuCodeList(list);
            redisUtil.setAuthRoleString(sr.getRoleCode(), sr);
            //菜单缓存到redis
            for (SystemMenu sm : menuList) {
                String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getMenuCode();
                String roleMenuIdCode = sr.getId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + sm.getId();
                List<String> smList = rsmbMap.get(roleMenuIdCode);
                sm.setButtonCodeList(smList);
                redisUtil.setAuthMenuString(roleMenuCode, sm);
            }
        }

        //按钮缓存到redis
        for (SystemButton sb : buttonList) {
            redisUtil.setAuthButtonString(sb.getButtonCode(), sb);
        }

        for (SystemDict sd : sdList) {
            redisUtil.setString(RedisKeyUtil.getPermissionDictKey(sd.getDictKey()), sd);
        }
    }
}
