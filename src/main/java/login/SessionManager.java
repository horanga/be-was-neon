package login;

import db.UserDatabaseImpl;
import http.UserDatabase;
import http.response.LoginResult;
import model.User;

import java.util.*;

public class SessionManager {
    public static final String LOGIN_SESSION_ID = "LOGIN_COOKIE";
    private static Map<String, User> loginSessionMap = new HashMap<>();

    public void createSession(User user, LoginResult response){
        String sid = getSid();

        UserDatabase userDatabase = new UserDatabaseImpl();
        Optional<User> userData = userDatabase.findUserById(user.getUserId());
        loginSessionMap.put(sid, userData.get());
        Cookie cookie = createCookie(sid);
        response.addCookie(LOGIN_SESSION_ID, cookie);
    }

    public boolean isSessionExisted(){
        return !loginSessionMap.isEmpty();
        //비어있으면 로그인 x 비어있지 않으며 로그인한 상태
    }

    public User getUserInfo(String sid){
        return loginSessionMap.get(sid);

    }

    public void deleteSession(){
        loginSessionMap.clear();
    }

    private String getSid() {
        return  UUID.randomUUID().toString();
    }

    private Cookie createCookie(String token) {
        return new Cookie(token);
    }

    public Map<String, User> getLoginSessionMap(){
        return Collections.unmodifiableMap(loginSessionMap);
    }

    public void clear(){
        loginSessionMap.clear();
    }
}
