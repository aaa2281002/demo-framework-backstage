package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.exception.login.LoginFailLimitException;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.encrypt.PasswordUtil;
import com.framework.common.util.encrypt.RSAEncrypt;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义身份验证实现类
 * @DateTime 2019/12/18 15:54
 * @Version 1.0
 */
@Component("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private HttpServletRequest httpServletRequest;
//    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private EnterpriseMapper enterpriseMapper;
//    @Autowired
//    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String url = httpServletRequest.getRequestURL().toString();
//        log.info("authenticate:自定义身份验证实现类:url:{}", url);
        log.info("authenticate:自定义身份验证实现类");
        String captchaCode = httpServletRequest.getParameter("captchaCode");
        String grapCode = httpServletRequest.getParameter("grapCode");
        if (StringUtils.isEmpty(captchaCode) || StringUtils.isEmpty(grapCode)) {
            throw new LoginFailLimitException("请输入验证码！");
        }
        String captchaCodeKey = RedisKeyUtil.getCaptchaKey(grapCode);
        String redisCaptchaCode = redisUtil.getString(captchaCodeKey);
        if (StringUtils.isEmpty(redisCaptchaCode)) {
            throw new LoginFailLimitException("验证码已过期！");
        }
        if (!captchaCode.equalsIgnoreCase(redisCaptchaCode)) {
            throw new LoginFailLimitException("验证码错误！");
        }
        String username = authentication.getName();
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("请输入登录账户！");
        }
        String password = String.valueOf(authentication.getCredentials());
        log.info("authenticate:password=" + password);
        if (StringUtils.isEmpty(password)) {
            throw new BadCredentialsException("请输入密码！");
        }
        String dPassword = null;
        try {
            dPassword = RSAEncrypt.decryptPrivate(password, PasswordUtil.privateKey);
        } catch (Exception e) {
            log.error("authenticate:error:{}", e.getMessage());
            throw new LoginFailLimitException(e.getMessage());
        } finally {
            log.info("authenticate:dPassword=" + dPassword);
        }
//        if (StringUtils.isEmpty(dPassword) ||
//                dPassword.length() < NumeralUtil.POSITIVE_EIGHT || dPassword.length() > NumeralUtil.POSITIVE_THIRTY_TWO) {
//            throw new BadCredentialsException("密码最少8位至最多32位!");
//        }
        UserDetails userDetails = null;
        //验证账户是否存在
        if (username != null) {
            userDetails = myUserDetailsService.loadUserByUsername(username);
        }
        log.info("authenticate:自定义身份验证，查询账号信息：" + userDetails);
        if (userDetails == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        //        //验证密码
        String md5Password = MD5Util.MD5EncodeToPassword(dPassword);
        log.info("authenticate:md5Password{}, userDetails.password:{}", md5Password, userDetails.getPassword());
        if (!userDetails.getPassword().equals(md5Password)) {
            log.error("authenticate:账号的密码错误:{}=={}", userDetails.getUsername(), md5Password);
            throw new BadCredentialsException("密码错误");
        }


        //验证API处理状态
//        log.info(userDetails.isEnabled());
//        log.info(userDetails.isAccountNonExpired());
//        log.info(userDetails.isAccountNonLocked());
//        log.info(userDetails.isCredentialsNonExpired());
        if (userDetails.isEnabled()) {
            log.error("authenticate:{}", userDetails.getUsername() + "账号已禁用");
            throw new DisabledException("账号已禁用");
        } else if (userDetails.isAccountNonExpired()) {
            log.error("authenticate:{}", userDetails.getUsername() + "账号已过期");
            throw new AccountExpiredException("账号已过期");
        } else if (userDetails.isAccountNonLocked()) {
            log.error("authenticate:{}", userDetails.getUsername() + "账号已锁定");
            throw new LockedException("账号已锁定");
        } else if (userDetails.isCredentialsNonExpired()) {
            log.error("authenticate:{}", userDetails.getUsername() + "凭证已过期");
            throw new CredentialsExpiredException("凭证已过期");
        }

        redisUtil.deleteKey(captchaCodeKey);
        return new RememberMeAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
//        return true;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
