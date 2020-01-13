package com.framework.web.config.initLogin;

import com.framework.common.util.other.NumeralUtil;
import com.framework.model.entity.login.UserPrincipal;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义用户登录信息业务实现类
 * @DateTime 2019/12/18 17:28
 * @Version 1.0
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SystemUserService systemUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义用户登录信息业务实现类");
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
