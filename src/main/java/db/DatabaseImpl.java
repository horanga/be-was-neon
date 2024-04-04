package db;

import http.Database;
import model.User;

import java.util.*;

public class DatabaseImpl implements Database {
    private static Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public Optional<User> findUserById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public void clear() {
        users.clear();
    }

    public Map<String, User> getUser() {
        return Collections.unmodifiableMap(users);
    }
}
