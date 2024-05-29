package com.company;

import com.company.service.UserService;
import com.company.service.UserServiceHibernateImpl;
import com.company.service.UserServiceJDBCImpl;
import com.company.util.Util;

public class Main {

    public static void main(String[] args) {
        script(new UserServiceJDBCImpl());
        script(new UserServiceHibernateImpl());
    }

    public static void script(UserService userService) {
        userService.createUserTable();
        userService.saveUser("Abc0", "Bcd", (byte) 35);
        userService.saveUser("Abc1", "Bcd", (byte) 45);
        userService.saveUser("Abc2", "Bcd", (byte) 55);
        userService.saveUser("Abc3", "Bcd", (byte) 65);

        System.out.println();
        userService.getAllUsers().forEach(System.out::println);

        userService.removeUserById(2);
        System.out.println();
        userService.getAllUsers().forEach(System.out::println);

        userService.clearUserTable();
        System.out.println();
        userService.getAllUsers().forEach(System.out::println);
    }
}