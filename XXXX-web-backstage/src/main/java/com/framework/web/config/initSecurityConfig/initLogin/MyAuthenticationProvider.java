package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.exception.captcha.CaptchaCodeException;
import com.framework.common.exception.user.LoginFailLimitException;
import com.framework.common.model.CaptchaImage;
import com.framework.common.util.encrypt.MD5Util;
import com.framework.common.util.encrypt.PasswordUtil;
import com.framework.common.util.encrypt.RSAEncrypt;
import com.framework.common.util.system.CaptchaUtil;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义身份验证实现类
 * @datetime 2019/12/18 15:54
 */
@Service("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate:自定义身份验证实现类");
        String username = authentication.getName();
        //验证密码
        String password = String.valueOf(authentication.getCredentials());
        try {
            password = RSAEncrypt.decryptPrivate(password, PasswordUtil.privateKey);
        } catch (Exception e) {
            log.error("authenticate:解密错误:{}", username);
            throw new LoginFailLimitException("密码错误！");
        }
        UserDetails userDetails = null;
        //验证账户是否存在
        if (username != null) {
            userDetails = myUserDetailsService.loadUserByUsername(username);
        }
        log.info("authenticate:自定义身份验证，查询账号信息：{}", userDetails);
        if (userDetails == null) {
            log.info("authenticate:账号不存在:{}", username);
            throw new UsernameNotFoundException("账号不存在");
        }

        //验证后台处理状态
//        System.out.println(userDetails.isEnabled());
//        System.out.println(userDetails.isAccountNonExpired());
//        System.out.println(userDetails.isAccountNonLocked());
//        System.out.println(userDetails.isCredentialsNonExpired());
        if (userDetails.isEnabled()) {
            log.info("authenticate:账号已被禁用:{}", username);
            throw new DisabledException("账号已被禁用");
        } else if (userDetails.isAccountNonExpired()) {
            log.info("authenticate:账号已过期:{}", username);
            throw new AccountExpiredException("账号已过期");
        } else if (userDetails.isAccountNonLocked()) {
            log.info("authenticate:账号已被锁定:{}", username);
            throw new LockedException("账号已被锁定");
        } else if (userDetails.isCredentialsNonExpired()) {
            log.info("authenticate:凭证已过期:{}", username);
            throw new CredentialsExpiredException("凭证已过期");
        }

        String md5Password = MD5Util.MD5EncodeToPassword(password);
        if (!userDetails.getPassword().equals(md5Password)) {
            log.error("authenticate:账号的密码错误:{}, 密码:{}, 输入密码:{}", username, password, md5Password);
            throw new BadCredentialsException("密码错误");
        }

        //验证验证码
        HttpSession session = httpServletRequest.getSession();
        CaptchaImage ciSession = (CaptchaImage) session.getAttribute(CaptchaUtil.CAPTCHA_SESSION_KEY);
        if (ciSession == null) {
            String t = "验证码不存在";
            log.error("authenticate:{}:{}", t, username);
            throw new CaptchaCodeException(t);
        }
        if (ciSession.isExpried()) {
            String t = "验证码已过期";
            log.error("authenticate:{}:{}", t, username);
            throw new CaptchaCodeException(t);
        }
        String captchaCode = httpServletRequest.getParameter(CaptchaUtil.CAPTCHA_CODE);
        if (StringUtils.isEmpty(captchaCode)) {
            String t = "验证码不能为空";
            log.error("authenticate:{}:{}", t, username);
            throw new CaptchaCodeException(t);
        }
        if (!captchaCode.equalsIgnoreCase(ciSession.getCode())) {
            String t = "验证码不匹配";
            log.error("authenticate:{}:{}:输入验证码:{}=系统验证码:{}", t, userDetails.getUsername(), captchaCode, ciSession.getCode());
            throw new CaptchaCodeException(t);
        }
        return new RememberMeAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.equals(authentication);
        return true;
    }
}
