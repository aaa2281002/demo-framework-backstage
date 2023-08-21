package com.framework.web.config.initDataConfig;

import com.framework.service.other.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initDataConfig
 * @description 初始化角色，菜单，按钮参数
 * @datetime 2019/10/11
 */
@Configuration
public class InitDataConfig implements CommandLineRunner {
    @Autowired
    private CacheService cacheServiceImpl;

    /**
     * @param args 1 参数数组
     * @titel 初始化角色，菜单，按钮到redis中
     * @description 初始化角色，菜单，按钮到redis中
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:15
     */
    @Override
    public void run(String... args) throws Exception {
        cacheServiceImpl.initCache();
    }
}
