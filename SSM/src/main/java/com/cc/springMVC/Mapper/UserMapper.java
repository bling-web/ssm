package com.cc.springMVC.Mapper;

import com.cc.springMVC.Domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    @Select("select * from User")
    List<User> findAll();

    @Insert("insert into user (picture,username,name,phone,email,password,sex,date)values (#{picture},#{username},#{name},#{phone},#{email},#{password}," +
            "#{sex},#{date})")
    void insert(User user);

    @Select("select username,phone,email from user where password=#{password}")
    List<User> verify(String password);


    @Select("select username,phone,email from user")
    List<User> verifyColumn();

    @Delete("delete from user where id=#{id}")
    void delete(String id);

    @Select("select * from user where id=#{id}")
    User findById(String id);

    @Update("update user set picture=#{picture},username=#{username},name=#{name},phone=#{phone},email=#{email},password=#{password},sex=#{sex},date=#{date} where id=#{id}")
    void update(User user);
}
