package com.framework.web.config.initLogin;

import com.framework.common.exception.CaptchaCodeException;
import com.framework.common.model.CaptchaImage;
import com.framework.common.util.system.CaptchaUtil;
import com.framework.common.util.encrypt.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义身份验证实现类
 * @DateTime 2019/12/18 15:54
 * @Version 1.0
 */
@Service("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("自定义身份验证实现类");
        String username = authentication.getName();
        UserDetails userDetails = null;
        //验证账户是否存在
        if (username != null) {
            userDetails = myUserDetailsService.loadUserByUsername(username);
        }
        System.out.println("自定义身份验证，查询账号信息：" + userDetails);
        if (userDetails == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        //验证后台处理状态
//        System.out.println(userDetails.isEnabled());
//        System.out.println(userDetails.isAccountNonExpired());
//        System.out.println(userDetails.isAccountNonLocked());
//        System.out.println(userDetails.isCredentialsNonExpired());
        if (userDetails.isEnabled()) {
            System.err.println(userDetails.getUsername() + "账号已被禁用");
            throw new DisabledException("账号已被禁用");
        } else if (userDetails.isAccountNonExpired()) {
            System.err.println(userDetails.getUsername() + "账号已过期");
            throw new AccountExpiredException("账号已过期");
        } else if (userDetails.isAccountNonLocked()) {
            System.err.println(userDetails.getUsername() + "账号已被锁定");
            throw new LockedException("账号已被锁定");
        } else if (userDetails.isCredentialsNonExpired()) {
            System.err.println(userDetails.getUsername() + "凭证已过期");
            throw new CredentialsExpiredException("凭证已过期");
        }

//        //验证密码
        String password = String.valueOf(authentication.getCredentials());
        String md5Password = MD5Util.MD5EncodeToPassword(password);
        if (!userDetails.getPassword().equals(md5Password)) {
            System.err.println(userDetails.getUsername() + "账号的密码错误:" + md5Password);
            throw new BadCredentialsException("密码错误");
        }

        //验证验证码
        HttpSession session = httpServletRequest.getSession();
        CaptchaImage ciSession = (CaptchaImage) session.getAttribute(CaptchaUtil.CAPTCHA_SESSION_KEY);
        if (ciSession == null) {
            String t = "验证码不存在";
            System.err.println(t);
            throw new CaptchaCodeException(t);
        }
        if (ciSession.isExpried()) {
            String t = "验证码已过期";
            System.err.println(t);
            throw new CaptchaCodeException(t);
        }
        String captchaCode = httpServletRequest.getParameter(CaptchaUtil.CAPTCHA_CODE);
        if (StringUtils.isEmpty(captchaCode)) {
            String t = "验证码不能为空";
            System.err.println(t);
            throw new CaptchaCodeException(t);
        }
        if (!captchaCode.equalsIgnoreCase(ciSession.getCode())) {
            String t = "验证码不匹配";
            System.err.println(t);
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
