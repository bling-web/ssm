package com.cc.work3.service;

import com.cc.work3.DAO.UserDao;
import com.cc.work3.DoMain.User;
import com.cc.work3.Utils.JdbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {

    public static boolean verifyUser(User user) {
        String username = user.getUsername();
        List<User> users = UserDao.queryUser(username);
        for (User dbuser : users) {
            if(dbuser.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;

    }
}
