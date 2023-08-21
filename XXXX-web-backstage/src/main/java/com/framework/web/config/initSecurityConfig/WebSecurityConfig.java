package com.framework.web.config.initSecurityConfig;

import com.framework.common.model.auth.IgnoredDataRoleAuth;
import com.framework.common.model.auth.IgnoredRoleAuth;
import com.framework.common.model.properties.IgnoredUrlsProperties;
import com.framework.common.model.properties.InitTokenProperties;
import com.framework.common.model.security.JwtConstant;
import com.framework.common.util.filter.FilterStringUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.service.init.JwtService;
import com.framework.service.system.SystemLogService;
import com.framework.service.system.SystemUserService;
import com.framework.web.config.initSecurityConfig.initLogin.MyAuthenticationFailHandler;
import com.framework.web.config.initSecurityConfig.initLogin.JWTAuthenticationFilter;
import com.framework.web.config.initSecurityConfig.initLogin.MyAuthenticationProvider;
import com.framework.web.config.initSecurityConfig.initLogin.MyAuthenticationSuccessHandler;
import com.framework.web.config.initSecurityConfig.initLogin.RestAccessDeniedHandler;
import com.framework.web.config.initSecurityConfig.initUrlAuth.MyAccessDecisionManager;
import com.framework.web.config.initSecurityConfig.initUrlAuth.MySecurityMetadataSource;
import com.framework.web.other.filter.ParameterValueFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initSecurityConfig
 * @Description web请求安全拦截配置
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private InterceptorConfig interceptorConfig;
//    @Value("${spring.security.remember-me-time}")//登录记住我选择，(单位：秒)
//    private Integer rememberMeTime;
//    @Autowired//自定义身份验证实现类
//    private MyAuthenticationProvider myAuthenticationProvider;
//    @Autowired//登录成功处理
//    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//    @Autowired//登录失败处理
//    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
//    @Autowired//退出处理
//    private MyAuthenticationLogoutHandler myAuthenticationLogoutHandler;
//    @Autowired//自定义用户登录信息业务实现类
//    private MyUserDetailsService myUserDetailsService;
//    @Autowired//自定义redis
//    private MyPersistentTokenRepository myPersistentTokenRepository;
//    @Autowired//数据库
//    private PersistentTokenRepository persistentTokenRepository;
//    @Autowired//记住我业务初始化设置
//    private RememberMeServices rememberMeService;
//    @Autowired
//    private ParameterValueFilter parameterValueFilter;
    @Autowired
    private JwtService jwtServiceImpl;
    @Autowired
    private JwtConstant jwtConstant;
    @Autowired//自定义身份验证实现类
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private PathMatcher pathMatcher;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemUserService systemUserServiceImpl;
    @Autowired
    private SystemLogService systemLogServiceImpl;
    @Autowired
    private InitTokenProperties initTokenProperties;
    @Autowired
    private IgnoredDataRoleAuth ignoredDataRoleAuth;
    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;
    @Autowired
    private IgnoredRoleAuth ignoredRoleAuth;
//    @Autowired
//    private ImageValidateFilter imageValidateFilter;
//    @Autowired
//    private MyAuthenticationFailHandler authenticationFailHandler;
//    @Autowired
//    private AuthenticationSuccessHandler authenticationSuccessHandler;
//    @Autowired
//    private RestAccessDeniedHandler restAccessDeniedHandler;
//    @Autowired
//    private AccessDecisionManager myAccessDecisionManager;
//    @Autowired
//    private FilterInvocationSecurityMetadataSource mySecurityMetadataSource;

    //初始化URL注入对象
    @Bean
    public PathMatcher pathMatcher() {
        return new AntPathMatcher();
    }

    //重实现springboot-web安全模块
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web层面的拦截，用来跳过的资源
        for (String item : ignoredUrlsProperties.getInitViewIgnoreUrl()) {
            web.ignoring()
                    .antMatchers(item);
        }
    }

    //重实现身份验证管理器生成器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        myAuthenticationProvider.setRedisUtil(redisUtil);
        auth.authenticationProvider(myAuthenticationProvider);
    }

    //重实现安全性设置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加不需要验证权限的URL
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        for (String url : ignoredUrlsProperties.getIgnoreUrl()) {
            registry.antMatchers(url).permitAll();
        }
        //使用这个是不会走多次拦截器。
        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(new MyAccessDecisionManager(ignoredDataRoleAuth));//决策管理器
                object.setSecurityMetadataSource(new MySecurityMetadataSource(pathMatcher, ignoredUrlsProperties, ignoredRoleAuth));//安全元数据源
                return object;
            }
        });
        http.formLogin()
                //没登录跳转提示
                .loginPage(FilterStringUtil.FILTER_STRING_NEED_LOGIN)
                //登录请求URL
                .loginProcessingUrl(FilterStringUtil.FILTER_STRING_LOGIN)
                .permitAll()
                //登录成功处理
                .successHandler(new MyAuthenticationSuccessHandler(initTokenProperties, systemLogServiceImpl, jwtConstant, jwtServiceImpl, redisUtil))
                //登录失败处理
                .failureHandler(new MyAuthenticationFailHandler(initTokenProperties, systemLogServiceImpl, systemUserServiceImpl, redisUtil))
                .and()
                // 允许网页iframe
                .headers().frameOptions().disable()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                //任何请求
                .anyRequest()
                // 需要身份认证
                .authenticated()
                .and()
                // 允许跨域
                .cors()
                .and()
                //关闭跨站请求防护
                .csrf().disable()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类 accessDeniedHandler
                .and().exceptionHandling().accessDeniedHandler(new RestAccessDeniedHandler())
                .and()
                // 添加自定义权限过滤器 myFilterSecurityInterceptor
                //FilterSecurityInterceptor(主要处理鉴权)
                //使用自定义URL拦截器的话。会出现走多次的情况。高并发的时候会出现性能损耗。
//                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
////                 短信验证码过滤器
                //AbstractAuthenticationProcessingFilter(主要处理登录)
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(channelSmsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(smsValidateFilter, UsernamePasswordAuthenticationFilter.class)
                // 图形验证码过滤器 imageValidateFilter
//                .addFilterBefore(imageValidateFilter, UsernamePasswordAuthenticationFilter.class)
                //添加参数过滤器
                .addFilterAfter(new ParameterValueFilter(pathMatcher, ignoredUrlsProperties), JWTAuthenticationFilter.class)
                // 添加JWT过滤器 除已配置的其它请求都需经过此过滤器
//                .addFilter(new JWTAuthenticationFilter(authenticationManager(), tokenProperties, redisTemplate, securityUtil));
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), initTokenProperties, redisUtil, ignoredDataRoleAuth,
                        this.ignoredUrlsProperties, pathMatcher, jwtConstant, jwtServiceImpl))
//                .addFilterAfter(null, JWTAuthenticationFilter.class)
        ;
//        http.sessionManagement().disable();
    }

}
