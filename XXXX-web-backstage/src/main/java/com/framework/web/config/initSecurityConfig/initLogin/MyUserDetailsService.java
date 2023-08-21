package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.util.other.NumeralUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.model.system.SystemUser;
import com.framework.service.system.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义用户登录信息业务实现类
 * @datetime 2019/12/18 17:28
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
    @Autowired
    private SystemUserService systemUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername：自定义用户登录信息业务实现类");
        SystemUser systemUser = systemUserServiceImpl.queryForLoginName(username);
        if (systemUser == null) {
            throw new UsernameNotFoundException("用户：" + username + "不存在！");
        }
        if (systemUser.getOperaterStatus().intValue() == NumeralUtil.NEGATIVE_ONE) {
            throw new UsernameNotFoundException("用户：" + username + "已被删除！");
        }
//        if (!systemUser.isEnabled()) {
//            System.out.println("用户已被禁用");
//            throw new DisabledException("用户已被禁用");
//        } else if (!systemUser.isAccountNonExpired()) {
//            System.out.println("账号已过期");
//            throw new AccountExpiredException("账号已过期");
//        } else if (!systemUser.isAccountNonLocked()) {
//            System.out.println("账号已被锁定");
//            throw new LockedException("账号已被锁定");
//        } else if (!systemUser.isCredentialsNonExpired()) {
//            System.out.println("凭证已过期");
//            throw new CredentialsExpiredException("凭证已过期");
//        }
//        String md5Password = MD5Util.MD5EncodeToPassword(password);
//        if (!systemUser.getPassword().equals("")) {
////            session.setAttribute("login_error", SystemError.USER_PASSWORD_ERROR.getMessage());
//            throw new BadCredentialsException("密码错误");
//        }
        return new UserPrincipal(systemUser);
    }
}
