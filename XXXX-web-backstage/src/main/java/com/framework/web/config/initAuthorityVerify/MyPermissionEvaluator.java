package com.framework.web.config.initAuthorityVerify;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.model.auth.IgnoredRoleAuth;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.model.system.SystemMenu;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @ClassName com.framework.web.config.initAuthorityVerify
 * @description 自定义权限验证实现类
 * @datetime 2019/12/22 14:57
 * @Version 1.0
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    private Logger log = LoggerFactory.getLogger(MyPermissionEvaluator.class);
    @Autowired
    private IgnoredRoleAuth ignoredRoleAuth;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param authentication     1 登录信息
     * @param targetDomainObject 2 类型代码
     * @param permission         3 权限代码
     * @return boolean 成功=true|失败=false
     * @titel 权限验证方法
     * @description 权限验证方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:11
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (permission == null) {
            return false;
        }
        UserPrincipal p = (UserPrincipal) authentication.getPrincipal();
        if (p == null) {
            return false;
        }
        if (ignoredRoleAuth.getAuthList().size() > NumeralUtil.POSITIVE_ZERO && ignoredRoleAuth.getAuthList().contains(p.getSystemUser().getRoleCode())) {
//            authentication.getAuthorities()
            return true;
        }

        //处理菜单权限
        if (SystemUtil.SYSTEM_MENU_NAME.equals(targetDomainObject)) {
            return is(p.getSystemUser(), permission, null);
        }
        //处理按钮权限
        String[] auths = permission.toString().split(SymbolUtil.NO_INPUT_METHOD_COLON);
        if (auths == null || auths.length < NumeralUtil.POSITIVE_TWO) {
            return false;
        }
        boolean is = is(p.getSystemUser(), auths[NumeralUtil.POSITIVE_ZERO], auths[NumeralUtil.POSITIVE_ONE]);
        if (!is) {
            return false;
        }
        String menuButtonCode = auths[NumeralUtil.POSITIVE_ZERO] + SymbolUtil.NO_INPUT_METHOD_COLON + auths[NumeralUtil.POSITIVE_ONE];
        String buttonStr = redisUtil.getAuthButtonString(menuButtonCode);
        //判断获取到redis按钮的值是否为空
        if (StringUtils.isEmpty(buttonStr)) {
            return false;
        }
        SystemMenu button = JSONObject.parseObject(buttonStr, SystemMenu.class);
        //判断按钮是否空和禁用
        if (button == null) {//|| button.getIsEnable().intValue() > NumeralUtil.POSITIVE_ONE
            return false;
        }
        return true;
    }

    //验证角色，菜单是否存在等处理
    private boolean is(SystemUser p, Object permission, String button) {
        String roleStr = redisUtil.getAuthRoleString(p.getRoleCode());
        //判断获取到redis角色的值是否为空
        if (StringUtils.isEmpty(roleStr)) {
            return false;
        }
        SystemRole systemRole = JSONObject.parseObject(roleStr, SystemRole.class);
        //判断角色是否为空
        if (systemRole == null) {//|| systemRole.getIsEnable().intValue() > NumeralUtil.POSITIVE_ONE
            return false;
        }
        List<String> menuCodeList = systemRole.getMenuCodeList();
        //判断菜单集合是否为空
        if (menuCodeList == null || menuCodeList.size() == NumeralUtil.POSITIVE_ZERO) {
            return false;
        }
        //判断该菜单代码集合是否有菜单代码
        if (!menuCodeList.contains(permission)) {
            return false;
        }
        String roleMenuCode = p.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + permission.toString();
        String menuStr = redisUtil.getAuthMenuString(roleMenuCode);
        //判断获取到redis菜单的值是否为空
        if (StringUtils.isEmpty(menuStr)) {
            return false;
        }
        //判断菜单是否空和禁用
        SystemMenu systemMenu = JSONObject.parseObject(menuStr, SystemMenu.class);
        if (systemMenu == null) {// || systemMenu.getIsEnable().intValue() > NumeralUtil.POSITIVE_ONE
            return false;
        }
        if (StringUtils.isEmpty(button)) {
            return true;
        }
        if (systemMenu.getButtonCodeList() == null || !systemMenu.getButtonCodeList().contains(button)) {
            return false;
        }
        return true;
    }

    /**
     * @param authentication 1 登录信息
     * @param targetId       2 权限编号
     * @param targetType     3 类型代码
     * @param permission     4 权限代码
     * @return boolean 成功=true|失败=false
     * @Titel 权限验证方法
     * @description 权限验证方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:12
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        log.info("账号:" + authentication.getName() + ",targetId:" + targetId + ",targetType:" + targetType + ",permission:" + permission);
        log.info("账号: {},targetId: {},targetType: {},permission: {}", authentication.getName(), targetId, targetType, permission);
        return this.hasPermission(authentication, targetType, permission);
    }
}
