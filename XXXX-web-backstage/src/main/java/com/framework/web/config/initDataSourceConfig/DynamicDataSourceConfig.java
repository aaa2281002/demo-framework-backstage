//package com.framework.web.config.initDataSourceConfig;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.config.initDataSourceConfig
// * @Description 初始化Mapper类
// * @DateTime 2019/10/11
// * @Version 1.0
// */
//@Configuration
//@EnableTransactionManagement
//@MapperScan(value = "com.framework.**")
//public class DynamicDataSourceConfig {
//    //扫描XML路径注入
//    @Value("${mybatis.mapper-locations}")
//    private String mapperLocations;
//
//    /**
//     * 只读数据源
//     *
//     * @return com.alibaba.druid.pool.DruidDataSource;
//     * @Author 邋遢龘鵺
//     */
//    @Bean(name = "readDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.read-one")
//    @Primary
//    public DataSource setReadDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 只写数据源
//     *
//     * @return com.alibaba.druid.pool.DruidDataSource;
//     * @Author 邋遢龘鵺
//     */
//    @Bean(name = "writeDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.write-one")
//    public DataSource setWriteDataSource() {
//        // DataSourceProperties //数据源属性类
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 根据对应的数据源配置Mapper所有xml路径
//     *
//     * @return org.mybatis.spring.SqlSessionFactoryBean;
//     * @throws Exception
//     * @Author 邋遢龘鵺
//     */
//    private SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // 配置mapper文件位置
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * 只读SqlSessionFactory配置
//     *
//     * @param dataSource
//     * @return org.apache.ibatis.session.SqlSessionFactory;
//     * @throws Exception
//     * @Author 邋遢龘鵺
//     */
//    @Bean(name = "readSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = this.getSqlSessionFactoryBean(dataSource);
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    /**
//     * 只写SqlSessionFactory配置
//     *
//     * @param dataSource
//     * @return org.apache.ibatis.session.SqlSessionFactory;
//     * @throws Exception
//     * @Author 邋遢龘鵺
//     */
//    @Bean(name = "writeSqlSessionFactory")
//    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = this.getSqlSessionFactoryBean(dataSource);
//        return sqlSessionFactoryBean.getObject();
//    }
//    /**
//     * @param dataSource : 数据源
//     * @return org.springframework.transaction.interceptor.TransactionInterceptor;
//     * @Titel 配置只读事务管理器
//     * @Description 配置事务拦截器
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/10/11
//     */
//    @Bean("readTransactionManager")
//    @Primary
//    public DataSourceTransactionManager readTransactionManager(@Qualifier("readDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    /**
//     * @param dataSource : 数据源
//     * @return org.springframework.transaction.interceptor.TransactionInterceptor;
//     * @Titel 配置只写事务管理器
//     * @Description 配置事务拦截器
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/10/11
//     */
//    @Bean("writeTransactionManager")
//    public DataSourceTransactionManager writeTransactionManager(@Qualifier("writeDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//    /**
//     * 初始化SQL会话模板
//     *
//     * @param readSqlSessionFactory
//     * @param writeSqlSessionFactory
//     * @return org.mybatis.spring.SqlSessionTemplate
//     * @throws Exception
//     */
//    @Bean(name = "sqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory readSqlSessionFactory, @Qualifier("writeSqlSessionFactory") SqlSessionFactory writeSqlSessionFactory) throws Exception {
//        DynamicSqlSessionTemplate sqlSessionTemplate = new DynamicSqlSessionTemplate(readSqlSessionFactory);
//        Map<String, SqlSessionFactory> targetSqlSessionFactorys = new HashMap<String, SqlSessionFactory>();
//        targetSqlSessionFactorys.put("readSqlSessionFactory", readSqlSessionFactory);
//        targetSqlSessionFactorys.put("writeSqlSessionFactory", writeSqlSessionFactory);
//        sqlSessionTemplate.setTargetSqlSessionFactorys(targetSqlSessionFactorys);
//        //设置默认数据源
//        sqlSessionTemplate.setDefaultTargetSqlSessionFactory(readSqlSessionFactory);
////        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(readSqlSessionFactory);
//        return sqlSessionTemplate;
//    }
//}
