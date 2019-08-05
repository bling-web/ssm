package com.cc.work3.DAO;

import com.cc.work3.DoMain.User;
import com.cc.work3.Utils.JdbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    static JdbcTemplate jdbcTemplate=new JdbcTemplate(JdbUtil.getDataSource());
    public static List<User> queryUser(String username) {
        String str="select password from user where username=?";
//        Object[] obj=new Object[]{username};
        return jdbcTemplate.query(str,new BeanPropertyRowMapper<>(User.class),username);


    }
}
