package com.company.service;

import com.company.bean.User;
import com.company.dao.UserDao;
import com.company.dao.UserDaoHibernateImpl;

import java.util.List;

public class UserServiceHibernateImpl implements UserService {

    private UserDao userDao = new UserDaoHibernateImpl();

    @Override
    public void createUserTable() {
        userDao.createUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void clearUserTable() {
        userDao.clearUserTable();
    }
}
