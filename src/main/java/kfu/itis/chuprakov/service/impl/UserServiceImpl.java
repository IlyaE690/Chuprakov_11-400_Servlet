package kfu.itis.chuprakov.service.impl;

import kfu.itis.chuprakov.dao.UserDao;
import kfu.itis.chuprakov.dao.impl.UserDaoImpl;
import kfu.itis.chuprakov.dto.UserDto;
import kfu.itis.chuprakov.entity.User;
import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(u -> new UserDto(u.getName(), u.getLogin())).collect(Collectors.toList());
    }

    @Override
    public void registerUser(String name, String lastname, String login, String password) {
        String hashedPassword = PasswordUtil.encrypt(password);

        User user = new User(null, name, lastname, login, hashedPassword);
        userDao.save(user);
    }

    @Override
    public boolean authenticate(String login, String password) {
        User user = userDao.getByLogin(login);
        if (user == null) {
            return false;
        }

        String hashedPassword = PasswordUtil.encrypt(password);
        return hashedPassword.equals(user.getPassword());
    }

    @Override
    public boolean userExists(String login) {
        return userDao.userExists(login);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        User user = userDao.getByLogin(login);
        if (user != null) {
            return new UserDto(user.getName(), user.getLogin());
        }
        return null;
    }
}

