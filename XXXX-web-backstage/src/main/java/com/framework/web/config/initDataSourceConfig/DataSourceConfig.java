package com.framework.web.config.initDataSourceConfig;

import com.framework.common.util.DataSourceUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initDataSourceConfig
 * @description 数据源初始化类
 * @datetime 2019/10/11
 */
@Configuration
//@EnableTransactionManagement
@MapperScan(value = "com.framework.**")
public class DataSourceConfig {
    //    扫描XML路径注入
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    /**
     * @return javax.sql.DataSource 返回数据源
     * @title 只读数据源
     * @description 只读数据源
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 17:25
     */
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read-one")
    @Primary
    public DataSource setReadDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @return javax.sql.DataSource 返回数据源
     * @title 只写数据源
     * @description 只写数据源
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 17:25
     */
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write-one")
    public DataSource setWriteDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @param dataSource 1 数据源
     * @return org.mybatis.spring.SqlSessionFactoryBean sql
     * @title 根据对应的数据源配置Mapper所有xml路径
     * @description 根据对应的数据源配置Mapper所有xml路径
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 17:18
     */
    private SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return sqlSessionFactoryBean;
    }

    /**
     * @param dynamicDataSource 1 数据源
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @titel 根据对应的数据源配置Mapper所有xml路径
     * @description 根据对应的数据源配置Mapper所有xml路径
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        // 设置mapper.xml的位置路径
        // 配置mapper文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return factoryBean.getObject();
    }

    /**
     * @param dynamicDataSource 1 动态数据源
     * @return org.springframework.transaction.PlatformTransactionManager 返回事务配置
     * @titel 事务配置
     * @description 事务配置
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    /**
     * @param readDataSource  1 只读数据源
     * @param writeDataSource 2 只写数据源
     * @return javax.sql.DataSource 返回设置数据源对象
     * @titel 初始化SQL会话模板
     * @description 初始化SQL会话模板
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 15:39
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("readDataSource") DataSource readDataSource, @Qualifier("writeDataSource") DataSource writeDataSource) throws Exception {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetSqlSessionFactorys = new HashMap<Object, Object>();
        targetSqlSessionFactorys.put(DataSourceUtil.READ_DATA_SOURCE, readDataSource);
        targetSqlSessionFactorys.put(DataSourceUtil.WRITE_DATA_SOURCE, writeDataSource);
        dynamicDataSource.setTargetDataSources(targetSqlSessionFactorys);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(readDataSource);
        return dynamicDataSource;
    }
}
