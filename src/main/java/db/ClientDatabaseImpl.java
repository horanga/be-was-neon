package db;

import http.ClientDatabase;
import login.Cookie;

import java.util.HashMap;
import java.util.Map;

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


}
