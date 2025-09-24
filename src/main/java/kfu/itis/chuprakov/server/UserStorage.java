package kfu.itis.chuprakov.server;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    public static Map<String, String> users = new HashMap<String, String>();

    public static void addUser(String login, String password) {
        users.put(login, password);
    }

    public static Map<String, String> getUsers() {
        return new HashMap<>(users);
    }
}
