package com.framework.web.config.initLogin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义用户详细信息检查器
 * @DateTime 2019/12/22 10:45
 * @Version 1.0
 */
@Configuration
public class MyUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails user) {
        if (user.isAccountNonLocked()) {
            System.err.println(user.getUsername() + "账号已被锁定");
            throw new LockedException("账号已被锁定");
        }

        if (user.isEnabled()) {
            System.err.println(user.getUsername() + "账号已被禁用");
            throw new DisabledException("账号已被禁用");
        }

        if (user.isAccountNonExpired()) {
            System.err.println(user.getUsername() + "账号已过期");
            throw new AccountExpiredException("账号已过期");
        }

        if (user.isCredentialsNonExpired()) {
            System.err.println(user.getUsername() + "凭证已过期");
            throw new CredentialsExpiredException("凭证已过期");
        }
    }
}
