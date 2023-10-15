package com.mobanc.wxdev.controller;

import com.mobanc.wxdev.dao.User;
import com.mobanc.wxdev.service.CourseService;
import com.mobanc.wxdev.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@Slf4j
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    private String receivedToUser = null;
//    private String receivedCreateTime = null;

    private String wxPusherUid = null;

    public void receiveValue(String toUser, String createTime) {
        receivedToUser = toUser;
//        receivedCreateTime = createTime;
    }

    public void sendWxPusherUid(String uid) {
        wxPusherUid = uid;
    }

    @GetMapping("/main")
    public String mainLog() {
        return "register.html";
    }

    @GetMapping("/register")
    public String register(
            @RequestParam("nickname") String nickname,
            @RequestParam(value = "option1", required = false) String option1,
            @RequestParam(value = "option2", required = false) String option2,
            @RequestParam(value = "option3", required = false) String option3,
            @RequestParam(value = "option4", required = false) String option4,
            @RequestParam(value = "option5", required = false) String option5,
            @RequestParam("password") String password) {
        log.warn("options==>"+option1 + option2 + option3 + option4 + option5);
        try {
            String required_course = courseService.selectBaseCourse();
            if (wxPusherUid != null) {
                if (!userService.checkUserExists(wxPusherUid)) {
                    // 存入数据库
                    if (Objects.equals(password, "")) { // 秘钥校验
                        String uid = wxPusherUid;
                        String username = nickname;     // 用于迭代校验（冗余的写法）
                        String selected_course = buildSelectedCourse(required_course, option1, option2, option3, option4, option5);
                        userService.addUser(new User(uid, username, selected_course));
                        return "redirect:/success";
                    }
                    return "redirect:/error3003";
                }
                return "redirect:/error3002";
            }
            return "redirect:/error3001";
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            wxPusherUid = null;
        }
        return "redirect:/error3001";
    }

    private String buildSelectedCourse(String requiredCourse, String... options) {
        StringBuilder selectedCourseBuilder = new StringBuilder(requiredCourse);
        for (String option : options) {
            if (option != null && !option.isEmpty()) {
                selectedCourseBuilder.append(option).append(",");
            }
        }
        return selectedCourseBuilder.toString();
    }

    @GetMapping("/error3001")
    public String errorPage3001() {
        return "error3001.html";
    }

    @GetMapping("/error3002")
    public String errorPage3002() {
        return "error3002.html";
    }

    @GetMapping("/error3003")
    public String errorPage3003() {
        return "error3003.html";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success.html";
    }
}
