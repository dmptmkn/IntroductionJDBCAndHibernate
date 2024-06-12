package com.company.service;

import com.company.bean.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceHibernateImplTest {

    private UserService userService = new UserServiceHibernateImpl();
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
        userService.dropUserTable();
        assertDoesNotThrow(userService::createUserTable);
    }

    @Test
    void dropUserTable() {
        assertDoesNotThrow(userService::dropUserTable);
    }

    @Test
    void saveUser() {
        User expectedUser = new User(1L, testName, testLastName, testAge);
        User actualUser = userService.getAllUsers().get(0);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void removeUserById() {
        userService.removeUserById(1);

        assertTrue(userService.getAllUsers().isEmpty());
    }

    @Test
    void getAllUsers() {
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void clearUserTable() {
        userService.clearUserTable();

        assertTrue(userService.getAllUsers().isEmpty());
    }
}