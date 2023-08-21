package com.framework.service.rocketmq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.List;


/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.rocketmq.config
 * @description 初始化类
 * @datetime 2023/4/9 11:19
 */
//@Configuration
public class RocketmqConfig {
//    /**
//     * 解决RocketMQ Jackson不支持Java时间类型配置
//     * 源码参考：{@link org.apache.rocketmq.spring.autoconfigure.MessageConverterConfiguration}
//     */
//    @Bean
//    @Primary
//    public RocketMQMessageConverter createRocketMQMessageConverter() {
//        RocketMQMessageConverter converter = new RocketMQMessageConverter();
//        CompositeMessageConverter compositeMessageConverter = (CompositeMessageConverter) converter.getMessageConverter();
//        List<MessageConverter> messageConverterList = compositeMessageConverter.getConverters();
//        for (MessageConverter messageConverter : messageConverterList) {
//            if (messageConverter instanceof MappingJackson2MessageConverter) {
//                MappingJackson2MessageConverter jackson2MessageConverter = (MappingJackson2MessageConverter) messageConverter;
//                ObjectMapper objectMapper = jackson2MessageConverter.getObjectMapper();
//                // 增加Java8时间模块支持，实体类可以传递LocalDate/LocalDateTime
//                objectMapper.registerModules(new JavaTimeModule());
//            }
//        }
//        return converter;
//    }

}
