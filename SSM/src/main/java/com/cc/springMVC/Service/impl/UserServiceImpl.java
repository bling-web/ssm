package com.cc.springMVC.Service.impl;

import com.cc.springMVC.Domain.User;
import com.cc.springMVC.Mapper.UserMapper;
import com.cc.springMVC.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private boolean flag;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public boolean verify(User user) {
        String password = user.getPassword();
        List<User> list = userMapper.verify(password);
        for (User u : list) {
            if(u!=null){
                if(u.getUsername().equals(user.getUsername())||
                        u.getPhone().equals(user.getUsername())||
                        u.getEmail().equals(user.getUsername())){
                    flag=true;
                    break;
                }
            }
        }
        return flag;

    }

    @Override
    public boolean verifyColumn(User user) {
        //调用mapper层,查询输入是否符合要求
        List<User> users = userMapper.verifyColumn();
        for (User u : users) {
            if(u.getUsername().equals(user.getUsername())
               ||u.getPhone().equals(user.getPhone())
               ||u.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void update(User user) {
        //调用mapper层完成更新功能
        userMapper.update(user);
    }

    @Override
    public User findById(String id) {
        //调用逻辑层完成查询功能
        User userList = userMapper.findById(id);
//        List<User> all = userMapper.findAll();
//        System.out.println(all);
        return userList;
    }

    @Override
    public void delete(String id) {
        //调用逻辑层完成删除功能
        userMapper.delete(id);
    }


}
