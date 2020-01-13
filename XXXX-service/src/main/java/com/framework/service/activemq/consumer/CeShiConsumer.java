package com.framework.service.activemq.consumer;

import com.framework.service.activemq.config.QueryConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.activemq.consumer
 * @Description 测试消费者
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
public class CeShiConsumer {

//    /**
//     * @param msg 1 消息字符串
//     * @Titel 订单消息队列业务处理
//     * @Description 订单消息队列业务处理
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/10/11
//     */
//    @JmsListener(destination = QueryConfig.ACTIVEMQ_BACKSTAGE_CESHI_NAME)
//    public void receive(String msg) {
//        System.out.println("activeMQ消费者:" + msg);
//    }
}
