package com.framework.web.config.initLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;

//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import java.sql.DatabaseMetaData;
//import java.sql.ResultSet;
//import java.sql.SQLException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 记住我初始化工具类
 * @DateTime 2019/12/20 14:34
 * @Version 1.0
 */
@Configuration
public class RememberMeConfig {
    public static final String REMEMBER_ME = "remember-me";//记住我关键字
    public static final String INTERNAL_SECRET_KEY = "web-security-login";
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyUserDetailsChecker myUserDetailsChecker;
    @Autowired
    private MyPersistentTokenRepository myPersistentTokenRepository;

    /**
     * @Titel 记住我业务令牌数据库存储方式初始化设置
     * @Description 记住我业务令牌数据库存储方式初始化设置
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 12:35
     * @return org.springframework.security.web.authentication.RememberMeServices
     */
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() throws SQLException {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        DatabaseMetaData dm = dataSource.getConnection().getMetaData();
//        ResultSet rs = dm.getTables(null, "demoframework", "persistent_logins", new String[]{"TABLE"});
//        // 表已存在
//        if (rs.next()) {
//            System.err.println("persistent_logins已表存在...");
//        } else {
//            // 设置创建表
//            tokenRepository.setCreateTableOnStartup(true);
//        }
//        tokenRepository.setDataSource(dataSource);
//        return tokenRepository;
//    }
//
//    /**
//     * @return org.springframework.security.web.authentication.RememberMeServices
//     * @Titel 记住我业务初始化设置-数据库实现
//     * @Description 记住我业务初始化设置-数据库实现
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/12/22 12:35
//     */
//    @Bean
//    public RememberMeServices rememberMeServices() {
//        //数据库
//        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
//        rememberMeTokenRepository.setDataSource(dataSource);
//        // 这里也注入了 UserDetailsSerice 实例
//        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
//                INTERNAL_SECRET_KEY, myUserDetailsService, rememberMeTokenRepository);
//
//
//        rememberMeServices.setParameter(REMEMBER_ME);
//        rememberMeServices.setUserDetailsChecker(myUserDetailsChecker);
//        return rememberMeServices;
//    }

    /**
     * @return org.springframework.security.web.authentication.RememberMeServices
     * @Titel 记住我业务初始化设置-redis实现
     * @Description 记住我业务初始化设置-redis实现
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 12:35
     */
    @Bean
    public RememberMeServices rememberMeServices() {
        //redis
        // 这里也注入了 UserDetailsSerice 实例
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
                INTERNAL_SECRET_KEY, myUserDetailsService, myPersistentTokenRepository);

        rememberMeServices.setParameter(REMEMBER_ME);
        rememberMeServices.setUserDetailsChecker(myUserDetailsChecker);
        return rememberMeServices;
    }


}
