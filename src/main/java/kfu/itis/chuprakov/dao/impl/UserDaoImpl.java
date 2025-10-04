package kfu.itis.chuprakov.dao.impl;

import kfu.itis.chuprakov.dao.UserDao;
import kfu.itis.chuprakov.entity.User;
import kfu.itis.chuprakov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    public List<User> getAll() {
        String sql =  "select * from users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("lastname"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    ));
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, lastname, login, password) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getById(Integer id) {
        String sql = "select * from users where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByLogin(String login) {
        String sql = "select * from users where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean userExists(String login) {
        return getByLogin(login) != null;
    }
}



