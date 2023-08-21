package com.framework.web.config.initDataSourceConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initDataSourceConfig
 * @description 动态数据源，每执行一次数据库，动态获取数据源
 * @datetime 2019/10/11
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * @return java.lang.Object 值
     * @titel 获取当前数据源
     * @description 获取当前数据源
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:23
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
