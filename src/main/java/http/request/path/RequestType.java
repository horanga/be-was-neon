package http.request.path;

import http.request.message.RequestLine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum RequestType {

    MEMBERSHIP("POST","/user/create", new MemberShip()),
    REGISTERERFORM("GET","/register.html", new RegisterForm()),
    CONTENT("GET","", new Content());

    private final String method;
    private final String uri;
    private final FilePath requestType;

    RequestType(String method, String uri, FilePath requestType) {
        this.method = method;
        this.uri = uri;
        this.requestType = requestType;
    }

    public static FilePath getFilePath(RequestLine requestLine) {
        List<RequestType> methodType = getMethod(requestLine);

        Optional<RequestType> matchedRequestType = methodType.stream()
                .filter(method ->
                        Arrays.stream(requestLine.getUri())
                                .anyMatch(i -> i.equals(method.uri)))
                .findFirst();

        RequestType finalRequestType = matchedRequestType.orElse(CONTENT);

        return finalRequestType.requestType;
    }

    private static List<RequestType> getMethod(RequestLine requestLine) {
        return Arrays.stream(RequestType.values())
                .filter(type -> type.isMethodMatched(requestLine))
                .collect(Collectors.toList());
    }

    private boolean isMethodMatched(RequestLine requestLine) {
        return requestLine.getRequest().contains(method);
    }
}
