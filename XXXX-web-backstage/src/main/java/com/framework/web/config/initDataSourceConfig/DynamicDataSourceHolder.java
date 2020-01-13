//package com.framework.web.config.initDataSourceConfig;
//
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.config.initDataSourceConfig
// * @Description 多数据进行切换工具类
// * @DateTime 2019/10/11
// * @Version 1.0
// */
//public class DynamicDataSourceHolder {
//    //使用ThreadLocal把数据源与当前线程绑定
//    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
//
//    /**
//     * 写入当前线程绑定方法对应的数据库注解名称@TargetDataSource(dataSource = "writeSqlSessionFactory")
//     *
//     * @param dataSourceName
//     * @Author 邋遢龘鵺
//     */
//    public static void setDataSource(String dataSourceName) {
//        dataSources.set(dataSourceName);
//    }
//
//    /**
//     * 获取当前线程绑定注解数据的名称@TargetDataSource(dataSource = "writeSqlSessionFactory")
//     *
//     * @return
//     * @Author 邋遢龘鵺
//     */
//    public static String getDataSource() {
//        return dataSources.get();
//    }
//
//    /**
//     * 清空当前线程绑定
//     *
//     * @Author 邋遢龘鵺
//     */
//    public static void clearDataSource() {
//        dataSources.remove();
//    }
//}
