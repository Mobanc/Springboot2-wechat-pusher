package com.mobanc.wxdev.wxpusher.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wxpusher")
public class WxPusherConfig {
    public static String appToken;
    public static String sendMessageUrl;
    public static String serverUrl;

    public void setAppToken(String appToken) {
        WxPusherConfig.appToken = appToken;
    }

    public void setSendMessageUrl(String sendMessageUrl) {
        WxPusherConfig.sendMessageUrl = sendMessageUrl;
    }

    public void setServerUrl(String serverUrl) {
        WxPusherConfig.serverUrl = serverUrl;
    }
}
