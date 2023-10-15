package com.mobanc.wxdev.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TextMsg extends Msg {
    private String toUserName; // 接收者微信号openid
    private String fromUserName; // 开发者微信号
    private long createTime; // 消息创建时间
    private String msgType = "text"; // 消息类型
    private String content; // 回复的消息内容

    public TextMsg(String toUserName, String fromUserName, String content) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = System.currentTimeMillis() / 1000L;
        this.content = content;
    }

    public String send() {
        String xml = "<xml>" +
                "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>" +
                "<CreateTime>" + createTime + "</CreateTime>" +
                "<MsgType><![CDATA[" + msgType + "]]></MsgType>" +
                "<Content><![CDATA[" + content + "]]></Content>" +
                "</xml>";
        return xml;
    }
}
