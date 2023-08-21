package com.framework.web.config.initRedisSessionConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;

import javax.annotation.PostConstruct;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initRedisSessionConfig
 * @description 自定义springsession时间初始化类
 * @datetime 2019/12/19 14:06
 */
@Configuration
public class MyRedisHttpSessionConfiguration {
    //设置redisSession存活时间，时间(单位：秒)
    @Value("${spring.session.maxInactiveIntervalInSeconds}")
    private Integer myMaxInactiveIntervalInSeconds;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    @PostConstruct
    private void afterPropertiesSet() {
        //设置redisSession存活时间，时间(单位：秒)
        redisIndexedSessionRepository.setDefaultMaxInactiveInterval(myMaxInactiveIntervalInSeconds);
    }
}
