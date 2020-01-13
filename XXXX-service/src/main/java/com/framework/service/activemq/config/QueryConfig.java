package com.framework.service.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.activemq.config
 * @Description 下单MQ业务规范类类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
public class QueryConfig {

    public static final String ACTIVEMQ_BACKSTAGE_CESHI_NAME = "ceshi.name";

    //队列名称
    @Bean
    public Queue backstageCeshiQueue() {
        return new ActiveMQQueue(ACTIVEMQ_BACKSTAGE_CESHI_NAME);
    }

}
