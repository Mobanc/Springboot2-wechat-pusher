package com.mobanc.wxdev.wxpusher.task;

import com.mobanc.wxdev.service.UserService;
import com.mobanc.wxdev.wxpusher.config.MyTaskConfig;
import com.mobanc.wxdev.wxpusher.utils.WxPusherSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Component
public class MyOnceTask {
    private final MyTaskConfig taskConfig;
    @Autowired
    UserService userService;

    @Autowired
    public MyOnceTask(MyTaskConfig taskConfig) {
        this.taskConfig = taskConfig;
    }

    @PostConstruct
    public void executeTask() {
        StringBuilder message = new StringBuilder();
        String messageAno="我们更新了推送机制，现在，您将在每天晚上的20:00会被推送下一天的内容。";
        if (taskConfig.isEnabled()) {
            // 执行只执行一次的任务逻辑
            List<String> uIds=userService.getAllUsers();
            uIds.forEach(uid->{
                WxPusherSender.sendMessage(Collections.singletonList(uid),null,messageAno,null);
            });
        }
    }
}