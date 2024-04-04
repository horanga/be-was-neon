package http.response.resource;

import http.request.message.RequestLine;
import http.response.resource.redirection.MemberShip;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum FileType {

    MEMBERSHIP("POST", Arrays.asList("/user/create"), new MemberShip()),
    LOGIN_RESULT("POST", Arrays.asList("/login.html"), new LoginResult()),
    LOGOUT_PAGE("POST", Arrays.asList("/logout.html"), new Logout()),
    REGISTERER_FORM("GET", Arrays.asList("/register.html", "/registration"), new RegisterForm()),
    LOGIN_PAGE("GET", Arrays.asList("/user/login.html", "/login"), new Login()),
    USER_LIST("GET", Arrays.asList("/user/list"), new UserList()),
    CONTENT("GET", Arrays.asList(""), new OtherPage());

    private final String method;
    private final List<String> uriList;
    private final File requestType;

    FileType(String method, List<String> uriList, File requestType) {
        this.method = method;
        this.uriList = uriList;
        this.requestType = requestType;
    }

    public static File getFile(RequestLine requestLine) {

        List<FileType> methodType = getMethod(requestLine);

        Optional<FileType> matchedRequestType = methodType.stream()
                .filter(fileType ->
                        Arrays.stream(requestLine.getUri())
                                .anyMatch(uri -> fileType.uriList.contains(uri)))
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
