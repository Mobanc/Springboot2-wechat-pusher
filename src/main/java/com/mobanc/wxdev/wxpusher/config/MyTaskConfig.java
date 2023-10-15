package com.mobanc.wxdev.wxpusher.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "my-once-task")
public class MyTaskConfig {
    private boolean enabled;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
