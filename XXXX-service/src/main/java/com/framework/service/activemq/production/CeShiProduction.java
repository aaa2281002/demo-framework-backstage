package com.framework.service.activemq.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.activemq.production
 * @Description 测试生产者
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
public class CeShiProduction {
    @Autowired
    private Queue backstageCeshiQueue;
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * @param msg 1 推送字符串
     * @return void
     * @Titel 发送即时回调通知消息队列
     * @Description 发送即时回调通知消息队列
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public void send(String msg) {
//        log.info("PlaceOrderNoticeProduction.send:发送MQ消息:msg={}", msg);
//        this.jmsTemplate.convertAndSend(this.backstageCeshiQueue, msg);
    }

    /**
     * @param msg 1 推送字符串
     * @Titel 延迟60秒推送回调通知消息队列
     * @Description 延迟60秒推送回调通知消息队列
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public void sendDelaySixtySeconds(String msg) {
//        this.send(msg, NumeralUtil.MILLISECOND_SIXTY_THOUSAND);
    }

    /**
     * @param msg   1 推送字符串
     * @param delay 2 延迟时间，毫秒单位
     * @Titel 发送延迟回调通知消息队列
     * @Description 发送延迟回调通知消息队列, (注意:使用该方法需要去activeMQ服务器上修改此activemq.xml配置文件.
     * 在 < broker > < / broker > 标签上添加schedulerSupport = " true " 内容,内容如下：
     * <broker xmlns="http://activemq.apache.org/schema/core" schedulerSupport="true"> )
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public void send(String msg, long delay) {
//        log.info("PlaceOrderNoticeProduction.send:发送MQ延时消息:msg={},delay={}", msg, delay);
//        jmsTemplate.send(this.backstageCeshiQueue, new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage tm = session.createTextMessage(msg);
//                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
//                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1 * 1000);
//                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 1);
//                return tm;
//            }
//        });
    }
}
