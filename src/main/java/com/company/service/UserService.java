package com.company.service;

import com.company.bean.User;

import java.util.List;

public interface UserService {

    void createUserTable();

    void dropUserTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void clearUserTable();
}
