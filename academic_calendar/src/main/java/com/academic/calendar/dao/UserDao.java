package com.academic.calendar.dao;

import com.academic.calendar.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 用户相关操作dao
 */
@Mapper
public interface UserDao {

    @Insert("insert into user(username,password,salt,email) " +
            "values(#{username},#{password},#{salt},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Select("select user_id,username,password,salt,email,create_time from user " +
            "where username=#{username}")
    User selectByName(String username);

    @Select("select user_id,username,password,salt,email,create_time from user " +
            "where email=#{email}")
    User selectByEmail(String email);

    @Select("select user_id,username,password,salt,email,create_time from user " +
            "where user_id=#{id}")
    User selectById(int id);



}
