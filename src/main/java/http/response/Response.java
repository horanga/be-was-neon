package http.response;

import http.response.header.Header;
import login.Cookie;

public class Response {

    private final Header header;
    private final byte[] file;
    private Cookie cookie;

    public Response(Header header, byte[] file) {
        this.header = header;
        this.file = file;
    }

    public Header getHeader() {
        return header;
    }

    public byte[] getFile() {
        return file;
    }

    public void setCookie(Cookie cookie){
        this.cookie = cookie;
    }
}
