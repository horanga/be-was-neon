package login;

import db.DatabaseImpl;
import model.User;

import java.util.Map;

public class LoginManager {
    public boolean login(String id, String password) {
        return isAcountValid(id, password);
    }

    private boolean isAcountValid(String id, String password) {
        DatabaseImpl databaseImpl = new DatabaseImpl();
        Map<String, User> users = databaseImpl.getUser();

        return users.entrySet().stream().anyMatch(user ->
                user.getKey().equals(id) && user.getValue().getPassword().equals(password));
    }

}

