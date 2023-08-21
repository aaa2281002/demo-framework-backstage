package com.framework.web.config.initRedisSessionConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initRedisSessionConfig
 * @description springsession和redis单机或集群初始化类, 因为使用了这个类初始化，所以吧RedisConfig注释掉
 * @datetime 2019/12/19 14:05
 */
@EnableRedisHttpSession
//@Configuration
public class RedisSessionConfig {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${spring.redis.entityttl}")
    private Integer entityttl;//存储时间

    /**
     * @param redisConnectionFactory 1 redis工厂参数对象
     * @return org.springframework.data.redis.cache.RedisCacheManager
     * @titel 设置cache缓存工厂存在时间，单位分
     * @description 设置cache缓存工厂存在时间，单位分
     * @author 邋遢龘鵺
     * @datetime 2020/1/13 14:45
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(entityttl))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).transactionAware().build();
    }

    /**
     * @return org.springframework.data.redis.serializer.RedisSerializer<java.lang.String>
     * @titel 设置redis缓存键格式
     * @description 设置redis缓存键格式
     * @author 邋遢龘鵺
     * @datetime 2020/1/13 14:43
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
//        return new Jackson2JsonRedisSerializer(Object.class);
    }

    /**
     * @return org.springframework.data.redis.serializer.RedisSerializer<java.lang.Object>
     * @titel 设置redis缓存值格式
     * @description 设置redis缓存值格式
     * @author 邋遢龘鵺
     * @datetime 2020/1/13 14:43
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
//        return new JdkSerializationRedisSerializer(getClass().getClassLoader());
    }


    /**
     * @return org.springframework.data.redis.core.RedisTemplate
     * @titel redis初始化设置, 解决redis插入中文乱码
     * @description redis初始化设置, 解决redis插入中文乱码
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:33
     */
    @Bean
    public RedisTemplate redisTemplateInit() {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);

//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();


        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @param redisConnectionFactory 1 redis链接工厂
     * @return org.springframework.data.redis.core.RedisTemplate
     * @titel redis初始化工厂设置
     * @description redis初始化工厂设置
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:33
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
     * @titel redis初始化工厂设置
     * @description redis初始化工厂设置
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:34
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
