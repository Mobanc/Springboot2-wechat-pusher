package com.mobanc.wxdev.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
public class CalDateAndWeekUtils {
    //计算周次
    private static final String START_DATE_STRING = "2023-09-04";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static int calculateWeekNumber(String inputDate) {
        try {
            Date startDate = dateFormat.parse(START_DATE_STRING);
            Date endDate = dateFormat.parse(inputDate);

            // Calculate the difference in milliseconds
            long difference = endDate.getTime() - startDate.getTime();

            // Convert the difference to weeks
            int weeks = (int) (difference / (7 * 24 * 60 * 60 * 1000)) + 1;

            return weeks;

        } catch (ParseException e) {
            e.printStackTrace();
            return -1;  // Return -1 for invalid input dates
        }
    }


    // 计算星期几
    public static int getDayOfWeek(String dateStr) {
        try {
            // Parse the input date string
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);

            // Create a Calendar instance and set the date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Get the day of the week as an integer (7: Sunday, 1: Monday, ..., 6: Saturday)
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // Convert the integer to the corresponding day of the week
            int[] weekdays = {7, 1, 2, 3, 4, 5, 6};
            return weekdays[dayOfWeek - 1];

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //获取当前日期
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String timestampToDateString(long timestamp) {
        Date date = new Date(timestamp * 1000);  // Convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static long dateStringToTimestamp(String dateStr) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析日期字符串为LocalDate对象
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 将LocalDate对象转换为时间戳（以天为单位）

        return date.toEpochDay();
    }
}

