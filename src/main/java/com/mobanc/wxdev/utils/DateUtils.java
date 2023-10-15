package com.mobanc.wxdev.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// 链式调用
public class DateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Date currentDate;

    private DateUtils(Date currentDate) {
        this.currentDate = currentDate;
    }

    public static DateUtils getCurrentDate() {
        return new DateUtils(new Date());
    }

    public DateUtils nextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        currentDate = calendar.getTime();
        return this;
    }
    public DateUtils nextDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        currentDate = calendar.getTime();
        return this;
    }

    public String format() {
        return sdf.format(currentDate);
    }
}
