package db;

import http.ClientDatabase;
import login.Cookie;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static login.SessionManager.LOGIN_SESSION_ID;

public class ClientDatabaseImpl implements ClientDatabase {
    private static Map<String, Cookie> cookieDatabase = new HashMap<>();

    @Override
    public void addCookie(String type, Cookie cookie) {
        cookieDatabase.put(type, cookie);
    }

    @Override
    public Cookie getCookie(String type) {
        return cookieDatabase.get(type);
    }

    @Override
    public void invalidateCookie(String type) {
        cookieDatabase.remove(type);
    }

    @Override
    public Map<String, Cookie> getCookieDatabase() {
        return Collections.unmodifiableMap(cookieDatabase);
    }

    @Override
    public void clear() {
        cookieDatabase.clear();
    }

    @Override
    public boolean isLoginCookieExisted() {
        return cookieDatabase.containsKey(LOGIN_SESSION_ID);
    }


}
