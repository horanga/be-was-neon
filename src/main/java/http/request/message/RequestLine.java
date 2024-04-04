package http.request.message;

import java.util.Arrays;

public class RequestLine {

    private final String requestLine;
    private final String method;
    private final String[] uri;

    public RequestLine(String requestLine, String method, String[] uri) {
        this.requestLine = requestLine;
        this.method = method;
        this.uri = uri;
    }

    public boolean isMethodValid(){
        return method.equalsIgnoreCase("GET")
                ||method.equalsIgnoreCase("POST");
    }

    public boolean isPostRequest() {
        return isMatchedMethod("POST");
    }

    public boolean isMatchedMethod(String method) {
        return this.method.equals(method);
    }

    public boolean isMatchedUri(String uri) {
        return requestLine.contains(uri);
    }

    public String[] getUri() {
        return Arrays.copyOf(uri, uri.length);
    }

    public String getRequestLine(){
        return requestLine;
    }
    public String getMethod(){
        return method;
    }

}
