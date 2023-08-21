package com.framework.service.rocketmq.production;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.enums.mq.RocketmqTypeEnum;
import com.framework.common.exception.mq.RocketException;
import com.framework.common.model.mq.MessageBody;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.other.UUIDUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.rocketmq.production
 * @description 测试生产者
 * @datetime 2023/4/9 11:20
 */
@Component
public class RocketMqProduction {
    private Logger log = LoggerFactory.getLogger(RocketMqProduction.class);
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * @param rocketmqTypeEnum 1 发送枚举状态
     * @param destination      2 发送目的地=topic:consumer
     * @param code             3 代码
     * @param payload          4 信息内容
     * @param msgSource        5 消息来源
     * @Title 发送消息，通用
     * @Description 发送消息，通用
     * @Author 龘鵺
     * @DateTime 2023/4/23 11:52
     */
    private void sendMsg(RocketmqTypeEnum rocketmqTypeEnum, String destination, String code, JSONObject payload, String msgSource) {
        String msgKey = UUIDUtil.getUUID();
        MessageBody msgBody = new MessageBody(msgKey, code, payload, msgSource);
        sendMsg(rocketmqTypeEnum, msgKey, destination, msgBody);
    }

    /**
     * @param rocketmqTypeEnum 1 发送枚举状态
     * @param msgKey           2 业务编号字符串
     * @param destination      3 发送目的地=topic:consumer
     * @param msgBody          4 消息正文
     * @Title 发送消息，通用
     * @Description 发送消息，通用
     * @Author 龘鵺
     * @DateTime 2023/4/24 11:51
     */
    private void sendMsg(RocketmqTypeEnum rocketmqTypeEnum, String msgKey, String destination, MessageBody msgBody) {
        log.info("RocketMqProduction=sendMsg=开始:msgBody:{}", JSONObject.toJSONString(msgBody));
        Message<MessageBody> message = MessageBuilder.withPayload(msgBody).setHeader("KEYS", msgKey).build();
        log.info("RocketMqProduction=sendMsg=开始: destination:{}, message:{}", destination, message);
        SendResult result = null;
        switch (rocketmqTypeEnum.getCode()) {
            case "ONEWAY":
                rocketMQTemplate.sendOneWay(destination, message);
                break;
            case "ASYNC":
                rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("RocketMqProduction:sendMsg:onSuccess:{}", sendResult.toString());
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("RocketMqProduction:sendMsg:onException:{}", ExceptionUtils.getStackTrace(throwable));
                        throw new RocketException(String.format("消息发送失败 sendMsg:onException:%s", destination));
                    }
                });
                break;
            case "SYNC":
                result = rocketMQTemplate.syncSend(destination, message);
                break;
        }
        log.info("RocketMqProduction=sendMsg=结束: result: {}, dest: {} msg: {}", JSONObject.toJSONString(result), destination,
                message);
    }

    /**
     * @param destination 1 发送目的地=topic:consumer
     * @param code        2 代码
     * @param payload     3 信息内容
     * @param msgSource   4 消费来源
     * @Title 同步发送
     * @Description 同步发送
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:02
     */
    public void syncSendMsg(String destination, String code, JSONObject payload, String msgSource) {
        sendMsg(RocketmqTypeEnum.code2, destination, code, payload, msgSource);
    }

    /**
     * @param topic     1 消费主题
     * @param tag       2 具体消费者
     * @param code      3 代码
     * @param payload   4 信息内容
     * @param msgSource 5 消费来源
     * @Title 同步发送消息, 会确认应答
     * @Description 同步发送消息, 会确认应答
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:01
     */
    public void syncSendMsg(String topic, String tag, String code, JSONObject payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + SymbolUtil.NO_INPUT_METHOD_COLON + tag;
        syncSendMsg(destination, code, payload, msgSource);
    }

    /**
     * @param destination 1 发送目的地=topic:consumer
     * @param code        2 代码
     * @param payload     3 信息内容
     * @param msgSource   4 消费来源
     * @Title 异步消息发送, 异步日志确认异常
     * @Description 异步消息发送, 异步日志确认异常
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:02
     */
    public void asyncSendMsg(String destination, String code, JSONObject payload, String msgSource) {
        sendMsg(RocketmqTypeEnum.code1, destination, code, payload, msgSource);
    }

    /**
     * @param topic     1 消费主题
     * @param tag       2 具体消费者
     * @param code      3 代码
     * @param payload   4 信息内容
     * @param msgSource 5 消费来源
     * @Title 异步消息发送, 异步日志确认异常
     * @Description 异步消息发送, 异步日志确认异常
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:01
     */
    public void asyncSendMsg(String topic, String tag, String code, JSONObject payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + SymbolUtil.NO_INPUT_METHOD_COLON + tag;
        asyncSendMsg(destination, code, payload, msgSource);
    }

    /**
     * @param destination 1 发送目的地=topic:consumer
     * @param code        2 代码
     * @param payload     3 信息内容
     * @param msgSource   4 消费来源
     * @Title 单向发送消息，不关注结果
     * @Description 单向发送消息，不关注结果
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:02
     */
    public void oneWaySendMsg(String destination, String code, JSONObject payload, String msgSource) {
        sendMsg(RocketmqTypeEnum.code0, destination, code, payload, msgSource);
    }

    /**
     * @param topic     1 消费主题
     * @param tag       2 具体消费者
     * @param code      3 代码
     * @param payload   4 信息内容
     * @param msgSource 5 消费来源
     * @Title 单向发送消息，不关注结果
     * @Description 单向发送消息，不关注结果
     * @Author 龘鵺
     * @DateTime 2023/4/23 12:01
     */
    public void oneWaySendMsg(String topic, String tag, String code, JSONObject payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + SymbolUtil.NO_INPUT_METHOD_COLON + tag;
        oneWaySendMsg(destination, code, payload, msgSource);
    }
}
