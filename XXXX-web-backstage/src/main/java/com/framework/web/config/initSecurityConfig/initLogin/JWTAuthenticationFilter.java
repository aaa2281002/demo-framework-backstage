package com.framework.web.config.initSecurityConfig.initLogin;

import com.alibaba.fastjson.JSON;
import com.framework.common.model.auth.IgnoredDataRoleAuth;
import com.framework.common.model.properties.IgnoredUrlsProperties;
import com.framework.common.model.properties.InitTokenProperties;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.ResponseUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisPrefixUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.model.security.JwtConstant;
import com.framework.model.login.CustomUser;
import com.framework.model.system.SystemUser;
import com.framework.service.init.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.PathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initAuthorityVerify
 * @Description JWT验证 https://qa.1r1g.com/sf/ask/3467083231/
 * @Date 2022/3/2 11:41
 * @Version 1.0
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private JwtService jwtServiceImpl;

    private JwtConstant jwtConstant;

    private IgnoredUrlsProperties ignoredUrlsProperties;

    private InitTokenProperties initTokenProperties;

    private RedisUtil redisUtil;

    private IgnoredDataRoleAuth ignoredDataRoleAuth;

    private PathMatcher pathMatcher;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, InitTokenProperties initTokenProperties
            , RedisUtil redisUtil, IgnoredDataRoleAuth ignoredDataRoleAuth, IgnoredUrlsProperties ignoredUrlsProperties, PathMatcher pathMatcher
            , JwtConstant jwtConstant, JwtService jwtServiceImpl) {
        super(authenticationManager);
        this.initTokenProperties = initTokenProperties;
        this.redisUtil = redisUtil;
        this.ignoredDataRoleAuth = ignoredDataRoleAuth;
        this.ignoredUrlsProperties = ignoredUrlsProperties;
        this.pathMatcher = pathMatcher;
        this.jwtConstant = jwtConstant;
        this.jwtServiceImpl = jwtServiceImpl;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        log.info("doFilterInternal:url={}", url);
        if (ignoredUrlsProperties.getInitIgnoreUrl() != null && ignoredUrlsProperties.getInitIgnoreUrl().size() > NumeralUtil.POSITIVE_ZERO) {
            for (String item : ignoredUrlsProperties.getInitIgnoreUrl()) {
                boolean is = pathMatcher.match(item, url);
                if (is) {
                    log.info("doFilterInternal:url:ignoredUrlsProperties");
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        String token = request.getHeader(jwtConstant.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtConstant.getHeader());
        }
        log.info("doFilterInternal:token={}", token);
//        boolean notValid = StringUtils.isBlank(token) || (!initTokenProperties.getRedis() && !token.startsWith(jwtConstant.getTokenSplit()));
        boolean notValid = StringUtils.isBlank(token);
        if (notValid) {
            log.info("doFilterInternal:notValid={}", notValid);
            ResponseUtil.out(response, new ResponseResult().fail().setCode("401").setMsg("请登录!"));
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token, response);
            if (authentication == null) {
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("doFilterInternal:{}", e.toString());
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletResponse response) {
        // 用户名
        SystemUser systemUser = null;
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (initTokenProperties.getRedis()) {
            String systemUserStr = redisUtil.getLoginTokenString(token);
            if (StringUtils.isBlank(systemUserStr)) {
                //登录失效直接提示
                ResponseUtil.out(response, new ResponseResult().fail().setCode("401").setMsg("登录已失效，请重新登录"));
                return null;
            }
            systemUser = JSON.parseObject(systemUserStr, SystemUser.class);
            long time = initTokenProperties.getTokenExpireTime() * NumeralUtil.POSITIVE_SIXTY;
            // 若未保存登录状态重新设置失效时间
            redisUtil.refreshUserExpireTime(systemUser.getLoginName(), time);
            redisUtil.refreshTokenExpireTime(token, time);
        } else {
            if (token.startsWith(jwtConstant.getTokenSplit())) {
                token = token.replace(jwtConstant.getTokenSplit(), "");
            }
            // JWT
            try {
                // 解析token
                Claims claims = jwtServiceImpl.getTokenClaim(token);
                // 获取用户JSON字符串
                String systemUserStr = redisUtil.getLoginUserString(claims.getSubject());
                if (StringUtils.isBlank(systemUserStr)) {
                    //登录失效直接提示
                    ResponseUtil.out(response, new ResponseResult().fail().setCode("401").setMsg("登录已失效，请重新登录"));
                    return null;
                }
                systemUser = JSON.parseObject(systemUserStr, SystemUser.class);
                if (jwtConstant.getSdl() && !token.equals(systemUser.getToken())) {
                    ResponseUtil.out(response, new ResponseResult().fail().setCode("401").setMsg("此账户已被他人登陆!"));
                    return null;
                }
            } catch (ExpiredJwtException e) {
                log.error("ExpiredJwtException:{}", e.toString());
                ResponseUtil.out(response, new ResponseResult().fail().setCode("401").setMsg("登录已失效，请重新登录"));
                return null;
            } catch (Exception e) {
                log.error("Exception:{}", e.toString());
                ResponseUtil.out(response, new ResponseResult().fail().setCode("500").setMsg("解析token错误"));
                return null;
            }
        }

        if (systemUser == null) {
            return null;
        }
        if (initTokenProperties.getStorePerms() && !ignoredDataRoleAuth.getAuthList().contains(systemUser.getRoleCode())) {
            String urlKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL,
                    RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE_URL, systemUser.getRoleCode());
            List<String> urlList = redisUtil.getList(urlKey, 0, -1);
            // 缓存了权限
            for (String item : urlList) {
                authorities.add(new SimpleGrantedAuthority(item));
            }
        }
        // JWT不缓存权限 读取权限数据 避免JWT长度过长
        CustomUser customUser = new CustomUser(systemUser, authorities);
        // 踩坑提醒 此处password不能为null
        return new UsernamePasswordAuthenticationToken(customUser, null, authorities);

    }
}
