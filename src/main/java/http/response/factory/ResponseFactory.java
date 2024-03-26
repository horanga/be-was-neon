package http.response.factory;

import http.request.message.RequestMessage;
import http.response.HttpResponse;
import http.response.MembershipResponse;
import http.response.PageResponse;

import java.util.Arrays;
import java.util.List;

public enum ResponseFactory {

    MEMBERSHIP_RESPONSE(new MembershipResponse(), Arrays.asList("POST")),
    PAGE_RESPONSE(new PageResponse(), Arrays.asList("GET"));


    HttpResponse httpResponse;
    List<String> keyword;

    ResponseFactory(HttpResponse httpResponse, List<String> keyword) {
        this.httpResponse = httpResponse;
        this.keyword = keyword;
    }

    public static HttpResponse chooseResponse(RequestMessage message) {
        String method = message.getMethod();

        return Arrays.stream(ResponseFactory.values())
                .filter(resonponse -> resonponse.matches(method))
                .map(i -> i.httpResponse).findFirst().get();
    }

    public boolean matches(String rquestMethod) {

        return keyword.stream()
                .anyMatch(method ->method.equals(rquestMethod.toUpperCase()));
    }
}
