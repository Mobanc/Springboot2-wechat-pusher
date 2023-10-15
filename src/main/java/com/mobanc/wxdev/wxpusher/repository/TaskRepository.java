package com.mobanc.wxdev.wxpusher.repository;

import com.mobanc.wxdev.service.UserService;
import com.mobanc.wxdev.utils.CronExpressionUtils;
import com.mobanc.wxdev.wxpusher.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;

@Repository
public class TaskRepository {
    @Autowired
    UserService userService;
    public String getCronExpression(String time, Status status) throws ParseException {
        return CronExpressionUtils.convertToCronExpression(time,status);
    }
}
