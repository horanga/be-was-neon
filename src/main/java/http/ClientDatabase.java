package http;

import login.Cookie;

import java.util.Map;

public interface ClientDatabase {
    void addCookie(String type, Cookie cookie);

    Cookie getCookie(String type);

    void invalidateCookie(String type);
    public Map<String, Cookie> getCookieDatabase();

    void clear();
    boolean isLoginCookieExisted();
}
