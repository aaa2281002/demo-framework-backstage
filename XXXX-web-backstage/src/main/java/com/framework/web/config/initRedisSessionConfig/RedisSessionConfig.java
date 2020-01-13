package com.framework.web.config.initRedisSessionConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initRedisSessionConfig
 * @Description springsession和redis单机或集群初始化类, 因为使用了这个类初始化，所以吧RedisConfig注释掉
 * @DateTime 2019/12/19 14:05
 * @Version 1.0
 */
@EnableRedisHttpSession
//@Configuration
public class RedisSessionConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @return org.springframework.data.redis.core.RedisTemplate
     * @Titel redis初始化设置, 解决redis插入中文乱码
     * @Description redis初始化设置, 解决redis插入中文乱码
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:33
     */
    @Bean
    public RedisTemplate redisTemplateInit() {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @param redisConnectionFactory 1 redis链接工厂
     * @return org.springframework.data.redis.core.RedisTemplate
     * @Titel redis初始化工厂设置
     * @Description redis初始化工厂设置
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:33
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * @param redisConnectionFactory 1 redis链接工厂
     * @return org.springframework.data.redis.core.StringRedisTemplate
     * @Titel redis初始化工厂设置
     * @Description redis初始化工厂设置
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:34
     */
    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
