package com.mobanc.wxdev.wxpusher.result.callback;

import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)  //标记为可忽略的注解，让Jackson忽略未知字段 （兼容性）
public class CallBackData {
    private int appId;
    private String appKey;
    private String appName;
    private int id;
    private String source;
    private String userName;
    private String userHeadImg;
    private long time;
    private String uid;
    private String extra;
}
