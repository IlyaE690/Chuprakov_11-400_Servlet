package kfu.itis.chuprakov.dao;

import kfu.itis.chuprakov.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    void save(User user);
    User getById(Integer id);
    User getByLogin(String login);
    boolean userExists(String login);
}
