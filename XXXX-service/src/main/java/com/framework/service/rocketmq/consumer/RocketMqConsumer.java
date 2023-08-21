package com.framework.service.rocketmq.consumer;

import com.framework.common.model.mq.MessageBody;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.rocketmq.consumer
 * @description 测试消费者
 * @datetime 2023/4/9 11:19
 */
//@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}", consumerGroup = "${rocketmq.consumer.group}",
//        selectorExpression = "${rocketmq.consumer.selectorExpression}",
//        nameServer = "${rocketmq.name-server}",
//        accessKey = "${rocketmq.producer.access-key}", secretKey = "${rocketmq.producer.secret-key}")
//@Component
public class RocketMqConsumer implements RocketMQListener<MessageBody> {
    private Logger log = LoggerFactory.getLogger(RocketMqConsumer.class);
    @Override
    public void onMessage(MessageBody message) {
        log.info("onMessage================================================");
        System.out.println(message.toString());
        log.info("onMessage================================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
