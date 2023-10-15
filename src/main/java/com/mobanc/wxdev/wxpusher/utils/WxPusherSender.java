package com.mobanc.wxdev.wxpusher.utils;

import com.mobanc.wxdev.wxpusher.config.WxPusherConfig;
import com.mobanc.wxdev.wxpusher.result.message.Message;
import com.mobanc.wxdev.wxpusher.result.message.MessageResult;
import com.mobanc.wxdev.wxpusher.result.message.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class WxPusherSender {
    static Message sender = null;
    public static Result<List<MessageResult>> sendMessage(List<String> uIds,String summary, String message, String url) {
        sender = new Message();
        sender.setUids(uIds);
        sender.setAppToken(WxPusherConfig.appToken);
        sender.setContentType(Message.CONTENT_TYPE_TEXT);
        if (summary != null) {
            sender.setSummary(summary);
        }
        if (message != null) {
            sender.setContent(message);
        }
        if (url != null) {
            sender.setUrl(url);
        }
        log.info(String.valueOf(sender));
        return WxPusherUtils.send(sender);
    }
}
