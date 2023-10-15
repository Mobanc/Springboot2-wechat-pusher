package com.mobanc.wxdev.service;

import com.mobanc.wxdev.dao.User;
import com.mobanc.wxdev.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CourseMapper courseMapper;

    public boolean checkUserExists(String uid) {
        int count = courseMapper.existsByUid(uid);
        return count > 0;
    }

    public void addUser(User user) {
        courseMapper.insertUser(user);
    }


    public List<String> getAllUsers(){
        return courseMapper.getAllUsers();
    }
}