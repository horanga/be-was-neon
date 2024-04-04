package http.request;

import http.request.message.RequestBody;
import http.request.message.RequestHeader;
import http.request.message.RequestLine;
import login.Cookie;

public class HttpRequest {
    private final RequestLine requestLine;
    private final RequestHeader requestHeader;
    private final RequestBody requestBody;
    private Cookie cookie;

    public HttpRequest(RequestLine requestLine, RequestHeader requestHeader, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    public boolean isPostRequest() {
        return requestLine.isPostRequest();
    }

    public boolean hasRequestBody() {
        return requestLine != null;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public String getSid() {
        return cookie.getSid();
    }

    public String[] getUserInfo() {
        return this.requestBody.getUserInfo();
    }

    public String getMimeType() {
        return this.requestHeader.getMimeType();
    }

    public String[] getUri() {
        return this.requestLine.getUri();
    }


}
