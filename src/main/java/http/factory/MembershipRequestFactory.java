package http.factory;

import http.request.HttpRequest;
import http.request.MembershipRequest;

public class MembershipRequestFactory implements RequestFactory {
    @Override
    public HttpRequest getRequest(String request) {
        return new MembershipRequest();
    }
}
