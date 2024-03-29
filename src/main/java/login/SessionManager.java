package login;

import http.response.LoginResult;
import model.User;

import java.util.*;

public class SessionManager {
    public static final String LOGIN_SESSION_ID = "LOGIN_COOKIE";
    private static List<Cookie> accountCookie = new ArrayList<>();
    private static Map<String, User> loginSessionMap = new HashMap<>();

    public void createSession(User user, LoginResult response){
        String sid = getSid();
        loginSessionMap.put(sid, user);
        Cookie cookie = createCookie(sid);
        response.addCookie(LOGIN_SESSION_ID, cookie);
    }

    public void deleteSession(){
        loginSessionMap.clear();
    }

    public User getUserLoginInfo(Cookie cookie){
        return loginSessionMap.getOrDefault(cookie.getSid(), new User());
    }

    public boolean isCookieExisted(Cookie cookie){
        return loginSessionMap.containsKey(cookie.getSid());
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


    public void clear() {
        loginSessionMap.clear();
    }
}
