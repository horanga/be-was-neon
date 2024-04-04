package http;

import model.User;
import java.util.*;

public interface UserDatabase {

    void addUser(User user);
    Optional<User> findUserById(String userId);
    Collection<User> findAll();
    void clear();
    Map<String, User> getUser();
}
