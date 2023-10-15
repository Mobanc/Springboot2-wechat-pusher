package com.mobanc.wxdev.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course_schedule")
public class Course {
    @TableId
    private Long id;
    private String weekNum;
    private String dayOfWeek;
    private String classNum;
    private String courseName;
    private String classroom;
    private String teacherName;
    private String classComment;
}