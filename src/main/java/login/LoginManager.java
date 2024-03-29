package login;

import db.ClientDatabaseImpl;
import db.UserDatabaseImpl;
import http.ClientDatabase;
import model.User;

import java.util.Map;

import static login.SessionManager.LOGIN_SESSION_ID;

public class LoginManager {
    public boolean login(String id, String password) {
        return isAcountValid(id, password);
    }

    public void logout() {
        SessionManager sessionManager = new SessionManager();
        sessionManager.deleteSession();
        ClientDatabase clientDatabase = new ClientDatabaseImpl();
        clientDatabase.invalidateCookie(LOGIN_SESSION_ID);
    }

    private boolean isAcountValid(String id, String password) {
        UserDatabaseImpl userDatabaseImpl = new UserDatabaseImpl();
        Map<String, User> users = userDatabaseImpl.getUser();

        return users.entrySet().stream().anyMatch(user ->
                user.getKey().equals(id) && user.getValue().getPassword().equals(password));
    }


}

