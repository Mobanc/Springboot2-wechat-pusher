package com.mobanc.wxdev.wxpusher.result.callback;

import lombok.Data;

@Data
public class AppSubscribeCallback {
    private String action;
    private CallBackData data;
}
