package com.company.service;

import com.company.bean.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceJDBCImplTest {

    private UserService userService = new UserServiceJDBCImpl();
    private String testName = "Ivan";
    private String testLastName = "Ivanov";
    private byte testAge = 33;

    @BeforeEach
    void testTuning() {
        userService.dropUserTable();
        userService.createUserTable();
        userService.saveUser(testName, testLastName, testAge);
    }

    @Test
    void createUserTable() {
        try {
            userService.dropUserTable();
            userService.createUserTable();
        } catch (Exception e) {
            fail("Произошла ошибка при создании таблицы");
        }
    }

    @Test
    void dropUserTable() {
        try {
            userService.dropUserTable();
        } catch (Exception e) {
            fail("Произошла ошибка при сбрасывании таблицы");
        }
    }

    @Test
    void saveUser() {
        try {
            User user = userService.getAllUsers().get(0);
            if (!testName.equals(user.getName()) || !testLastName.equals(user.getLastname()) || testAge != user.getAge())
                fail("Некорректное добавление пользователя в базу данных");
        } catch (Exception e) {
            fail("Произошла ошибка при добавлении пользователя");
        }
    }

    @Test
    void removeUserById() {
        try {
            userService.removeUserById(1);
            List<User> allUsers = userService.getAllUsers();
            if (!allUsers.isEmpty()) fail("Некорректное удаление пользователя");
        } catch (Exception e) {
            fail("Произошла ошибка при удалении пользователя");
        }
    }

    @Test
    void getAllUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
            if (allUsers.size() != 1) fail("Некорректное получение полного списка пользователей");
        } catch (Exception e) {
            fail("Произошла ошибка при получении полного списка пользователей");
        }
    }

    @Test
    void clearUserTable() {
        try {
            userService.clearUserTable();
            List<User> allUsers = userService.getAllUsers();
            if (!allUsers.isEmpty()) fail("Некорректное очищение таблицы пользователей");
        } catch (Exception e) {
            fail("Произошла ошибка при очищении таблицы пользователей");
        }
    }
}