package http;

import login.Cookie;
public interface ClientDatabase {
    void addCookie(String type, Cookie cookie);

    Cookie getCookie(String type);
}
