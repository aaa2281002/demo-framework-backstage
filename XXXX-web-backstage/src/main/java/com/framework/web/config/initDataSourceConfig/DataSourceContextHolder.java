package com.framework.web.config.initDataSourceConfig;

import com.framework.common.util.DataSourceUtil;
import com.framework.common.util.other.NumeralUtil;
import org.apache.commons.lang3.RandomUtils;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initDataSourceConfig
 * @Description 动态数据源上下文管理：设置数据源，获取数据源，清除数据源
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class DataSourceContextHolder {
    // 存放当前线程使用的数据源类型
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * @param type 1 值
     * @Titel 设置数据源
     * @Description 设置数据源
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:25
     */
    public static void setDataSource(String type) {
        contextHolder.set(type);
    }

    /**
     * @return java.lang.String
     * @Titel 获取数据源
     * @Description 获取数据源
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:24
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * @Titel 清除数据源
     * @Description 清除数据源
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:26
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

    /**
     * @Titel 设置只读库取数据
     * @Description 采用简单生成随机数的方式切换不同的只读库
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:27
     */
    public static void setRead() {
        if (RandomUtils.nextInt(NumeralUtil.POSITIVE_ZERO, NumeralUtil.POSITIVE_TWO) > NumeralUtil.POSITIVE_ZERO) {
            DataSourceContextHolder.setDataSource(DataSourceUtil.READ_DATA_SOURCE);
        } else {
            DataSourceContextHolder.setDataSource(DataSourceUtil.READ_DATA_SOURCE);
        }
    }
}
