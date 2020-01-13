package com.framework.web.config.initRedisSessionConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;

import javax.annotation.PostConstruct;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initRedisSessionConfig
 * @Description 自定义springsession时间初始化类
 * @DateTime 2019/12/19 14:06
 * @Version 1.0
 */
@Configuration
public class MyRedisHttpSessionConfiguration {
    //设置redisSession存活时间，时间单位秒
    @Value("${spring.session.maxInactiveIntervalInSeconds}")
    private Integer myMaxInactiveIntervalInSeconds;
    @Autowired
    private RedisIndexedSessionRepository redisIndexedSessionRepository;

    @PostConstruct
    private void afterPropertiesSet() {
        //设置redisSession存活时间，时间单位秒
        redisIndexedSessionRepository.setDefaultMaxInactiveInterval(myMaxInactiveIntervalInSeconds);
    }
}
