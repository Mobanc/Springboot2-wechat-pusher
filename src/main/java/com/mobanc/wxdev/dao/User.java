package com.mobanc.wxdev.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("user_info")
public class User {
    private String uid;
    private String username;
    private String selectedCourse;
}
