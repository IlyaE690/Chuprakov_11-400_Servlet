package kfu.itis.chuprakov.server;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    public static Map<String, String> users = new HashMap<String, String>();

    public static void addUser(String login, String password) {
        users.put(login, password);
    }

    public static boolean isValidUser(String login, String password) {
        String password1 = users.get(login);
        return password1 != null && password1.equals(password);
    }

    public static boolean userExists(String login) {
        return users.containsKey(login);
    }
}
