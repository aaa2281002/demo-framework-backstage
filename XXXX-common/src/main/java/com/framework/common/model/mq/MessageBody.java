package com.framework.common.model.mq;

import java.io.Serializable;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.model.mq
 * @description 消息正文
 * @datetime 2023/4/23 11:30
 */
public class MessageBody implements Serializable {
    private static final long serialVersionUID = -1L;
    // 消息id
    private String messageId;
    // body组装时间
    private long timestamp;
    // 代码
    private String code;
    // 来源 附加信息
    private String msgSource;
    // overload
    private Object data;

    public MessageBody() {
    }

    public MessageBody(String msgKey, Object data, String msgSource) {
        this.messageId = msgKey;
        this.data = data;
        this.msgSource = msgSource;
        this.timestamp = System.currentTimeMillis();
    }

    public MessageBody(String msgKey, String code, Object data, String msgSource) {
        this.messageId = msgKey;
        this.code = code;
        this.data = data;
        this.msgSource = msgSource;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsgSource() {
        return msgSource;
    }

    public void setMsgSource(String msgSource) {
        this.msgSource = msgSource == null ? null : msgSource.trim();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
                "messageId='" + messageId + '\'' +
                ", timestamp=" + timestamp +
                ", code='" + code + '\'' +
                ", msgSource='" + msgSource + '\'' +
                ", data=" + data +
                '}';
    }
}
