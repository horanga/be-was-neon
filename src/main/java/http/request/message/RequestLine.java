package http.request.message;

import java.util.Arrays;

public class RequestLine {

    private final String requestLine;
<<<<<<< HEAD
    private final String[] uri;
    private final String mimeType;


    public RequestLine(String requestLine, String[] uri, String mimeType) {
        this.requestLine = requestLine;
        this.uri = uri;
        this.mimeType = mimeType;
    }

    public boolean hasMatchingPath(List<String> uriList) {
        return Arrays.stream(uri).anyMatch(uriList::contains)
                || uriList.stream().anyMatch(requestLine::contains);
=======
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
>>>>>>> fd0d5d7 (refactor: HttpRequest에 메서드 캡슐화)
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

<<<<<<< HEAD
    public String getRequestLine() {
=======
    public String getRequestLine(){
>>>>>>> fd0d5d7 (refactor: HttpRequest에 메서드 캡슐화)
        return requestLine;
    }
    public String getMethod(){
        return method;
    }

}
