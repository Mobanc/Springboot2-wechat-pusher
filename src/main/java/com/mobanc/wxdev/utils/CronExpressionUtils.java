package com.mobanc.wxdev.utils;

import com.mobanc.wxdev.wxpusher.status.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CronExpressionUtils {
    /**
     * 将指定时间转换成Cron表达式
     * @param time 时间字符串，格式为"H:mm"
     * @param status 状态是否需要返回提前30分钟的格式，格式为"Status.NORMAL_TIME OR Status.MINUS_THIRTY_MIN_TIME"
     * @return Cron表达式
     * @throws ParseException 解析异常
     */
    public static String convertToCronExpression(String time, Status status) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Date date = sdf.parse(time);

        // 使用Calendar类获取小时和分钟
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (status == Status.MINUS_THIRTY_MIN_TIME) {
            // 提前30分钟
            if (minute >= 30) {
                minute -= 30;
            } else {
                hour -= 1;
                minute += 30;
            }
        }

        // 构造Cron表达式
        String cronExpression = String.format("0 %d %d ? * *", minute, hour);
        return cronExpression;
    }
}