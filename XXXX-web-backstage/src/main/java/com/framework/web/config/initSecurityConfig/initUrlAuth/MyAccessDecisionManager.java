package com.framework.web.config.initSecurityConfig.initUrlAuth;

import com.framework.common.model.auth.IgnoredDataRoleAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initSecurityConfig.initUrlAuth
 * @Description 权限管理决断器，判断用户拥有的权限或角色是否有资源访问权限
 * @Date 2022/3/2 15:03
 * @Version 1.0
 */
//@Component("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
    private Logger log = LoggerFactory.getLogger(MyAccessDecisionManager.class);
    @Autowired
    private IgnoredDataRoleAuth ignoredDataRoleAuth;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("MyAccessDecisionManager.decide");
        if (configAttributes == null) {
            return;
        }
//        SystemUser systemUser = ((UserPrincipal) authentication.getPrincipal()).getSystemUser();
//        if (ignoredDataRoleAuth.getAuthList().contains(systemUser.getRoleCode())) {
//            return;
//        }
//        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
//        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
//        while (iterator.hasNext()) {
//            ConfigAttribute c = iterator.next();
//            String needPerm = c.getAttribute();
//            for (GrantedAuthority ga : authentication.getAuthorities()) {
//                // 匹配用户拥有的ga 和 系统中的needPerm
//                if (needPerm.trim().equals(ga.getAuthority())) {
//                    return;
//                }
//            }
//        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        log.info("MyAccessDecisionManager.supports1");
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        log.info("MyAccessDecisionManager.supports2");
        return true;
    }
}
