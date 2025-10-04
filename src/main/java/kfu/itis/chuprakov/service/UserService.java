package kfu.itis.chuprakov.service;

import kfu.itis.chuprakov.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    void registerUser(String name, String lastname, String login, String password);

    boolean authenticate(String login, String password);

    boolean userExists(String login);

    UserDto getUserByLogin(String login);
}