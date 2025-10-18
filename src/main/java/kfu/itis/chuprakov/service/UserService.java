package kfu.itis.chuprakov.service;

import kfu.itis.chuprakov.dto.UserDto;
import kfu.itis.chuprakov.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    void registerUser(String name, String lastname, String login, String password, String path);

    boolean authenticate(String login, String password);

    boolean userExists(String login);

    User getUserByLogin(String login);
}