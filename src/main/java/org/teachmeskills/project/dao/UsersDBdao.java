package org.teachmeskills.project.dao;

import org.springframework.stereotype.Component;
import org.teachmeskills.project.exceptions.DuplicateUserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsersDBdao implements UsersDao {
    private Connection connection;

    // Сюда прилетает бин из RootConfiguration помеченный как "prototype", инжектим через конструктор:
    public UsersDBdao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getUsersStatusID(String login, String password) {
        int usersStatus = 0;
        try {
            String sql = "SELECT users_status_id FROM users WHERE login = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setInt(2, password.hashCode());
            ResultSet result = statement.executeQuery();
            while (result.next())
                usersStatus = result.getInt("users_status_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersStatus;
    }

    @Override
    public void setUser(String login, String password) throws DuplicateUserException {
        try {
            String sql = "INSERT INTO users VALUES ( ?, ?, 2)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setInt(2, password.hashCode());
            statement.execute();
        } catch (SQLException e) {
            throw new DuplicateUserException();
        }
    }
}
