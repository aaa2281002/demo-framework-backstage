package com.framework.web.config.initSecurityConfig.initLogin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义用户详细信息检查器
 * @datetime 2019/12/22 10:45
 */
@Configuration
public class MyUserDetailsChecker implements UserDetailsChecker {
    private Logger log = LoggerFactory.getLogger(MyUserDetailsChecker.class);
    @Override
    public void check(UserDetails user) {
        if (user.isAccountNonLocked()) {
            log.error("账号已被锁定:{}", user.getUsername());
            throw new LockedException("账号已被锁定");
        }

        if (user.isEnabled()) {
            log.error("账号已被禁用:{}", user.getUsername());
            throw new DisabledException("账号已被禁用");
        }

        if (user.isAccountNonExpired()) {
            log.error("账号已过期:{}", user.getUsername());
            throw new AccountExpiredException("账号已过期");
        }

        if (user.isCredentialsNonExpired()) {
            log.error("凭证已过期:{}", user.getUsername());
            throw new CredentialsExpiredException("凭证已过期");
        }
    }
}
