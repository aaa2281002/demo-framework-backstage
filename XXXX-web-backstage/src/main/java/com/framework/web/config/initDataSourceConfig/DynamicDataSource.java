package com.framework.web.config.initDataSourceConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initDataSourceConfig
 * @Description 动态数据源，每执行一次数据库，动态获取数据源
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * @return java.lang.Object 值
     * @Titel 获取当前数据源
     * @Description 获取当前数据源
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:23
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
