package com.company.dao;

import com.company.bean.User;
import com.company.util.Util;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getJDBCConnection();

    @Override
    @SneakyThrows
    public void createUserTable() {
        String query = "CREATE table IF NOT EXISTS user (id BIGINT primary key auto_increment, name VARCHAR(45), lastName VARCHAR(45), age TINYINT(3))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    @Override
    @SneakyThrows
    public void dropUserTable() {
        String query = "DROP table IF EXISTS user";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO user(name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        String query = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                allUsers.add(new User(id, name, lastName, age));
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return allUsers;
    }

    @Override
    @SneakyThrows
    public void clearUserTable() {
        String query = "TRUNCATE table user";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
}
