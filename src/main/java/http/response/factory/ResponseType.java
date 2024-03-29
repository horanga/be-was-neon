package http.response.factory;

import db.DatabaseImpl;
import http.request.HttpRequest;
import http.request.message.RequestLine;
import http.response.HttpResponse;
import http.response.LoginResult;
import http.response.Membership;
import http.response.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ResponseType {


    MEMBERSHIP_RESPONSE(new Membership(new DatabaseImpl()), "POST", "user/create"),
    LOGIN_RESPONSE(new LoginResult(), "POST", "login.html"),
    PAGE_RESPONSE(new Page(), "GET", "");

    private final HttpResponse httpResponse;
    private final String method;
    private final String uri;


    ResponseType(HttpResponse httpResponse, String method, String uri) {
        this.httpResponse = httpResponse;
        this.method = method;
        this.uri = uri;
    }

    public static ResponseType chooseResponse(HttpRequest httpRequest) {

        RequestLine requestLine = httpRequest.getRequestLine();

        List<ResponseType> methodMatchedResponses = getMatchedResponses(requestLine);

        ResponseType defaultResponse = PAGE_RESPONSE;


        return methodMatchedResponses.stream()
                .filter(type -> requestLine.isMatchedUri(type.uri)).findFirst()
                .orElse(defaultResponse);

    }

    private static List<ResponseType> getMatchedResponses(RequestLine requestLine) {
        List<ResponseType> methodMatchedResponses = Arrays.stream(ResponseType.values())
                .filter(response -> requestLine.isMatchedMethod(response.method))
                .collect(Collectors.toList());
        return methodMatchedResponses;
    }

    public HttpResponse getHttpResponse(){
        return httpResponse;
    }
}
