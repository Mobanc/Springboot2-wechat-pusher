package com.mobanc.wxdev.service;

import com.mobanc.wxdev.dao.Course;
import com.mobanc.wxdev.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> getTodayCourse(String uid, int week, int dayOfWeek) {
        return courseMapper.getTodayCourse(uid, week, dayOfWeek);
    }

    public String getClassTime(String classNum){
        return courseMapper.getClassTime(classNum);
    }

    public String selectBaseCourse(){
        return courseMapper.selectBaseCourse();
    };
}