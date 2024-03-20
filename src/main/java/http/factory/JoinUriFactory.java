package http.factory;

import http.request.HttpRequest;
import http.request.Membership;

public class JoinUriFactory implements RequestFactory {
    @Override
    public HttpRequest getRequest(String request) {
        return new Membership();
    }

}
