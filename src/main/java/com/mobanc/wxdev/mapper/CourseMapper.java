package com.mobanc.wxdev.mapper;

import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mobanc.wxdev.dao.Course;
import com.mobanc.wxdev.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @Select("SELECT * FROM " +
            "course_schedule " +
            "WHERE FIND_IN_SET( #{week}, week_num ) > 0 " +
            "AND day_of_week = #{dayOfWeek} " +
            "AND FIND_IN_SET(" +
            "course_name,( SELECT selected_course FROM user_info WHERE uid = #{uid} ))>0")
    List<Course> getTodayCourse(@Param("uid") String uid, @Param("week") int week, @Param("dayOfWeek") int dayOfWeek);

    @Select("SELECT COUNT(*) FROM user_info WHERE uid = #{uid}")
    int existsByUid(@Param("uid") String uid);

    @Insert("INSERT INTO user_info (uid, username, selected_course) " +
            "VALUES (#{uid}, #{username}, #{selectedCourse})")
    int insertUser(User user);

    @Select("SELECT class_time " +
            "FROM course_time " +
            "where class_num = #{class_num}")
    String getClassTime(@Param("class_num") String classNum);

    @Select("SELECT uid FROM user_info")
    List<String> getAllUsers();

    @Select("select base from base_course where cid = '1'")
    String selectBaseCourse();

}