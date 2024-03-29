package http.response.resource;

import http.request.message.RequestLine;
import http.response.resource.redirection.MemberShip;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum FileType {

    MEMBERSHIP("POST", "/user/create", new MemberShip()),
    LOGIN_RESULT("POST", "/login.html", new LoginResult()),
    REGISTERER_FORM("GET", "/register.html", new RegisterForm()),
    LOGIN_PAGE("GET", "/user/login.html", new Login()),
    CONTENT("GET", "", new OtherPage());

    private final String method;
    private final String uri;
    private final File requestType;

    FileType(String method, String uri, File requestType) {
        this.method = method;
        this.uri = uri;
        this.requestType = requestType;
    }

    public static File getFile(RequestLine requestLine) {

        List<FileType> methodType = getMethod(requestLine);

        Optional<FileType> matchedRequestType = methodType.stream()
                .filter(method ->
                        Arrays.stream(requestLine.getUri())
                                .anyMatch(i -> i.equals(method.uri)))
                .findFirst();

        FileType finalRequestType = matchedRequestType.orElse(CONTENT);

        return finalRequestType.requestType;
    }

    private static List<FileType> getMethod(RequestLine requestLine) {
        return Arrays.stream(FileType.values())
                .filter(type -> requestLine.isMatchedMethod(type.method))
                .collect(Collectors.toList());
    }
}
