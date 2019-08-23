package com.cc.springMVC.Service;

import com.cc.springMVC.Domain.User;

import java.util.List;

public interface UserService {

    List findAll();

    void insert(User user);

    boolean verify(User user);


    boolean verifyColumn(User user);


    void delete(String id);

    User findById(String id);
}
