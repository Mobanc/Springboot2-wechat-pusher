package com.mobanc.wxdev.wxpusher.task;

import com.mobanc.wxdev.dao.Course;
import com.mobanc.wxdev.service.CourseService;
import com.mobanc.wxdev.service.UserService;
import com.mobanc.wxdev.utils.DateUtils;
import com.mobanc.wxdev.wxpusher.repository.TaskRepository;
import com.mobanc.wxdev.wxpusher.utils.WxPusherSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.mobanc.wxdev.utils.CalDateAndWeekUtils.*;

@Component
@EnableScheduling
@Slf4j
public class Publish {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    List<String> uIds;
    Course courses;

    StringBuilder message = new StringBuilder();

    @Scheduled(cron = "0 0 20 * * ?")
    public void executeTaskLastNight() {
        try {
            courses = new Course();
//            String currentDate = getCurrentDate();
            String tomorrowDate = DateUtils.getCurrentDate().nextDay(1).format();
            int week = calculateWeekNumber(tomorrowDate);
            int dayOfWeek = getDayOfWeek(tomorrowDate);
            uIds = new ArrayList<>();
            uIds = userService.getAllUsers();
            int finalDayOfWeek = dayOfWeek;
            uIds.forEach(uid -> {
                System.out.println(uid + "==>");
//                List<Course> todayCourse = courseService.getTodayCourse(uid, week, dayOfWeek);
                List<Course> todayCourse = courseService.getTodayCourse(uid, week, finalDayOfWeek);
                todayCourse.forEach(System.out::println);
                message.append("明日课程推送\n").append(getCurrentDate()).append(" 第 ").append(week).append(" 周").append(" 星期 ").append(finalDayOfWeek).append(" :").append("\n\n");
                todayCourse.forEach(course -> {
                    message.append("课程：").append(course.getCourseName()).append("\n")
                            .append("教室：").append(course.getClassroom()).append("\n")
                            .append("教师：").append(course.getTeacherName()).append("\n")
                            .append("课次：").append(course.getClassNum()).append("\n")
                            .append("\n");
                });
                todayCourse.forEach(System.out::println);
                WxPusherSender.sendMessage(Collections.singletonList(uid),null, message.toString(), null);
                message.setLength(0);
            });

            log.warn("@@@@@@@@@@@@@@@@@@@@@@发布定时任务");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            uIds = null;
            courses = null;
        }
    }

    //    @Scheduled(cron = "0 50 7 * * ?")  // 设置定时执行的时间表达式，此处表示每天上午7:50点执行
    @Scheduled(cron = "0 50 7 * * ?")
    public void executeTaskEveryDay() {
        try {
            courses = new Course();
            String currentDate = getCurrentDate();
            int week = calculateWeekNumber(currentDate);
            int dayOfWeek = getDayOfWeek(currentDate);
            if("2023-10-08".equals(currentDate)){
                dayOfWeek=5;
            }
            uIds = new ArrayList<>();
            uIds = userService.getAllUsers();
            int finalDayOfWeek = dayOfWeek;
            uIds.forEach(uid -> {
                System.out.println(uid + "==>");
//                List<Course> todayCourse = courseService.getTodayCourse(uid, week, dayOfWeek);
                List<Course> todayCourse = courseService.getTodayCourse(uid, week, finalDayOfWeek);
                todayCourse.forEach(System.out::println);
                message.append(getCurrentDate()).append(" 第 ").append(week).append(" 周").append(" 星期 ").append(finalDayOfWeek).append(" :").append("\n\n");
                todayCourse.forEach(course -> {
                    message.append("课程：").append(course.getCourseName()).append("\n")
                            .append("教室：").append(course.getClassroom()).append("\n")
                            .append("教师：").append(course.getTeacherName()).append("\n")
                            .append("课次：").append(course.getClassNum()).append("\n")
                    .append("\n");
                });
                todayCourse.forEach(System.out::println);
                WxPusherSender.sendMessage(Collections.singletonList(uid),null, message.toString(), null);
                message.setLength(0);
            });

            log.warn("@@@@@@@@@@@@@@@@@@@@@@发布定时任务");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            uIds = null;
            courses = null;
        }
    }



    @Scheduled(cron = "0 0 8 * * ?")
    public void executeTask1_2() {
        sendPerCourse("1-2");
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void executeTask3_4() {
        sendPerCourse("3-4");
    }

    @Scheduled(cron = "0 30 13 * * ?")
    public void executeTask5_6() {
        sendPerCourse("5-6");
    }

    @Scheduled(cron = "0 0 15 * * ?")
    public void executeTask7_8() {
        sendPerCourse("7-8");
    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void executeTask9_10() {
        sendPerCourse("9-10");
    }

//    @Scheduled(cron = "0 50 15 * * ?")
    public void executeTaskOnly() {
        String message = "国庆周结束，预计下周开始正常推送，这是一条部署测试消息，以后不会再推送，祝大家前程似锦，心想事成！";
        uIds = userService.getAllUsers();
        uIds.forEach(uid -> {
            WxPusherSender.sendMessage(Collections.singletonList(uid),null, message, null);
        });
    }

    void sendPerCourse(String classNum) {
        StringBuilder summary = new StringBuilder();
        StringBuilder message = new StringBuilder();
        String currentDate = getCurrentDate();
        int week = calculateWeekNumber(currentDate);
        int dayOfWeek = getDayOfWeek(currentDate);
        if("2023-10-08".equals(currentDate)){
            dayOfWeek=5;
        }
        // 获取所有用户
        uIds = new ArrayList<>();
        uIds = userService.getAllUsers();
        int finalDayOfWeek = dayOfWeek;
        uIds.forEach(uid -> {
            List<Course> todayCourse = courseService.getTodayCourse(uid, week, finalDayOfWeek);
            todayCourse.forEach(course -> {
                if (Objects.equals(classNum, course.getClassNum())) {
                    summary.append(course.getClassroom())
                            .append(" ")
                            .append(course.getCourseName())
                            .append(" 第")
                            .append(classNum).append("节 ");

                    message.append(getCurrentDate()).append(" 第 ").append(week)
                            .append(" 周")
                            .append(" 星期 ")
                            .append(finalDayOfWeek)
                            .append(" :")
                            .append("\n\n");

                    message.append("课程：").append(course.getCourseName()).append("\n")
                            .append("教室：").append(course.getClassroom()).append("\n")
                            .append("教师：").append(course.getTeacherName()).append("\n")
                            .append("课次：").append(course.getClassNum()).append("\n")
                            .append("\n");
                    WxPusherSender.sendMessage(Collections.singletonList(uid),summary.toString(), message.toString(), null);
                }
                summary.setLength(0);
                message.setLength(0);
            });
        });
    }

    //    @Scheduled(fixedDelay = 60000)  // 每隔1分钟执行一次
    //    @Scheduled(cron = "#{@taskRepository.getCronExpression()}")
    //    @Scheduled(cron = "0 * * * * ?")
    //    public void executeTask() {
    //        // 获取当前时间
    //        LocalDateTime currentTime = LocalDateTime.now();
    ////        courseService.getClassTime()
    //        List<Course> todayCourse = courseService.getTodayCourse("uid", 5, 4);
    //        todayCourse.forEach(course->{
    //            String classNum = course.getClassNum();
    //        });
    //    }
}
