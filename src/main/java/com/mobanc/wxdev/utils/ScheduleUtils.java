package com.mobanc.wxdev.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ScheduleUtils {
    public static LocalDateTime calculateTaskTriggerTime(LocalDateTime classStartTime) {
        // 计算每节课开始前30分钟的时间点
        return classStartTime.minusMinutes(30);
    }
}
