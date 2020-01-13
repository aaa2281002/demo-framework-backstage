package com.framework.web.config.initSecurityConfig;

import com.framework.common.util.filter.FilterStringUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.web.config.initLogin.*;
import com.framework.web.other.filter.ParameterValueFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initSecurityConfig
 * @Description web请求安全拦截配置
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    //    @Autowired
//    private InterceptorConfig interceptorConfig;
    @Value("${spring.security.remember-me-time}")//登录记住我选择，单位秒
    private Integer rememberMeTime;
    @Autowired//自定义身份验证实现类
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired//登录成功处理
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired//登录失败处理
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired//退出处理
    private MyAuthenticationLogoutHandler myAuthenticationLogoutHandler;
    @Autowired//自定义用户登录信息业务实现类
    private MyUserDetailsService myUserDetailsService;
    @Autowired//自定义redis
    private MyPersistentTokenRepository myPersistentTokenRepository;
    @Autowired//数据库
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired//记住我业务初始化设置
    private RememberMeServices rememberMeService;
    @Autowired
    private ParameterValueFilter parameterValueFilter;
//    @Autowired
//    private TestFilter testFilter;

    //重实现springboot-web安全模块
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web层面的拦截，用来跳过的资源
        web.ignoring()
                .antMatchers(FilterStringUtil.FILTER_STRING_ALL_FAVICON_ICO);
//                .antMatchers("/assets/**")
//                .antMatchers("/captcha.jpg");
    }

    //重实现身份验证管理器生成器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    //重实现安全性设置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //以下的请求都不需要认证
        http
                .authorizeRequests()
                .antMatchers(FilterStringUtil.FILTER_STRING_RESOURCES).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_CSS).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_IMG).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_JS).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_GOOGLEV).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_FAVICON_ICO).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_LOGIN_PAGE).permitAll()
                .antMatchers(FilterStringUtil.FILTER_STRING_DEFAULT_CAPTCHA)
                .permitAll().anyRequest()
                .authenticated();
        //请求之前拦截处理器
//        http.addFilterBefore(testFilter, UsernamePasswordAuthenticationFilter.class);
        //请求之后拦截处理器
//        http.addFilter()
        http.addFilterAfter(parameterValueFilter, LogoutFilter.class);
        //登录处理
        http.formLogin()
                .loginPage(FilterStringUtil.FILTER_STRING_LOGIN)
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //成功跳转
                .successForwardUrl(FilterStringUtil.FILTER_STRING_INDEX)
                //成功处理
                .successHandler(myAuthenticationSuccessHandler)
                //失败处理
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll();
        //退出处理
        http.logout()
                .logoutSuccessHandler(myAuthenticationLogoutHandler)
//                .deleteCookies(RememberMeConfig.REMEMBER_ME)
                .invalidateHttpSession(true);
        //记住账号处理
        //记住我相关配置-数据库四线
//        http.rememberMe()
////                .rememberMeCookieName(RememberMeConfig.REMEMBER_ME)
//                .rememberMeServices(rememberMeService)
//                .key(RememberMeConfig.INTERNAL_SECRET_KEY)
//                //设置TokenRepository
//                .tokenRepository(persistentTokenRepository)//设置操作表的Repository，数据库格式存储
//                // 配置UserDetailsService
////                .userDetailsService(myUserDetailsService)
//                // 配置Cookie过期时间
//                .tokenValiditySeconds(rememberMeTime);

        //记住我相关配置-redis实现
        //RememberMeAuthenticationFilter=AbstractRememberMeServices.autoLogin=UserDetailsChecker.check
        http.rememberMe()
//                .rememberMeCookieName(RememberMeConfig.REMEMBER_ME)
                .rememberMeServices(rememberMeService)
                .key(RememberMeConfig.INTERNAL_SECRET_KEY)
                //设置TokenRepository
                .tokenRepository(myPersistentTokenRepository)//redis格式存储
                // 配置UserDetailsService
//                .userDetailsService(myUserDetailsService)
                // 配置Cookie过期时间,单位秒
                .tokenValiditySeconds(rememberMeTime);

        //关闭csrf保护，
        http.csrf().disable();
        //关闭安全注入的头headers，解决同域名下iframe请求处理
        http.headers().frameOptions().sameOrigin();
        // session管理

//        http.sessionManagement().invalidSessionUrl("/loginPage?invalid");
//        http.sessionManagement().maximumSessions(NumeralUtil.POSITIVE_ONE).maxSessionsPreventsLogin(true).expiredUrl("/loginPage");
        http.sessionManagement()
                .sessionFixation().changeSessionId()
                // session失效后跳转
                .invalidSessionUrl(FilterStringUtil.FILTER_STRING_LOGIN_PAGE_INVALID)
                // 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
                .maximumSessions(NumeralUtil.POSITIVE_ONE).expiredUrl(FilterStringUtil.FILTER_STRING_LOGIN_PAGE);

        //以下代码屏蔽security安全拦截，跨域，header头部一些处理
//        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll()
//                .and().headers().frameOptions().disable().and().csrf().disable();
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
