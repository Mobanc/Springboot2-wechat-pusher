package com.mobanc.wxdev.wxpusher.controller;

import com.mobanc.wxdev.controller.RegisterController;
import com.mobanc.wxdev.wxpusher.result.callback.AppSubscribeCallback;
import com.mobanc.wxdev.wxpusher.config.WxPusherConfig;
import com.mobanc.wxdev.wxpusher.utils.WxPusherSender;
import com.mobanc.wxdev.wxpusher.utils.WxPusherUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SubController {
    RegisterController registerController;

    @Autowired
    public SubController(RegisterController registerController) {
        this.registerController = registerController;
    }

    @PostMapping("/wechat")
    private AppSubscribeCallback mjuCallBack(@RequestBody String json) throws Exception {
        System.out.println(json);
        AppSubscribeCallback callback = WxPusherUtils.parseAppSubscribeCallback(json);
        System.out.println(callback);

        //发送消息
        String uid = callback.getData().getUid();
        List<String> uIds = new ArrayList<>();
        String message = "若您是第一次关注，请进入上方链接注册，注册成功后即可成功订阅课程。";
        // /main是一个controller，拼接的是服务器的地址，代表了这个请求由谁（/main）去处理。
        log.warn("serverUrl==>"+WxPusherConfig.serverUrl);
        String url = WxPusherConfig.serverUrl + "/main";
        uIds.add(uid);
        log.warn("UIDS===>" + uIds);
        registerController.sendWxPusherUid(uid);

        WxPusherSender.sendMessage(uIds,null, message, url);

        return callback;
    }
}
