package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.model.system.SystemUser;
import com.framework.service.system.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义用户登录信息业务实现类
 * @DateTime 2019/12/18 17:28
 * @Version 1.0
 */
@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemUserService systemUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername:自定义用户登录信息业务实现类");
        String flagKey = "loginFailFlag:" + username;
        String value = redisUtil.getString(flagKey);
        Long timeRest = redisUtil.getExpire(flagKey, TimeUnit.MINUTES);
        if (StringUtils.isNotBlank(value)) {
            // 超过限制次数
            throw new BadCredentialsException("登录错误次数超过限制，请" + timeRest + "分钟后再试");
        }
//        User user;
//        if (NameUtil.mobile(username)) {//判断是否手机号
//            user = userService.findByMobile(username);
//        } else if (NameUtil.email(username)) {//判断是否邮箱
//            user = userService.findByEmail(username);
//        } else {//自定义账户名
//            user = userService.findByUsername(username);
//        }
        SystemUser systemUser = systemUserServiceImpl.queryForLoginName(username);
        if (systemUser == null) {
//            throw new UsernameNotFoundException("用户：" + username + "不存在！");
            return null;
        }
        if (systemUser.getOperaterStatus().intValue() == NumeralUtil.NEGATIVE_ONE) {
            throw new UsernameNotFoundException("用户：" + username + "已注销！");
        }
//        if (!systemUser.isEnabled()) {
//            log.error("用户已被禁用");
//            throw new DisabledException("用户已被禁用");
//        } else if (!systemUser.isAccountNonExpired()) {
//            log.error("账号已过期");
//            throw new AccountExpiredException("账号已过期");
//        } else if (!systemUser.isAccountNonLocked()) {
//            log.error("账号已被锁定");
//            throw new LockedException("账号已被锁定");
//        } else if (!systemUser.isCredentialsNonExpired()) {
//            log.error("凭证已过期");
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
