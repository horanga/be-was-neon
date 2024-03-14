package http.factory;

import http.request.HttpRequest;
import http.request.JoinPageRequest;
import http.request.PostPageRequest;
import util.Parsor;

public class PageRequestFactory implements RequestFactory {
    @Override
    public HttpRequest getRequest(String requestHeader) {
        String request = Parsor.parse(requestHeader);
        if (request.equals("index.html")) {
            return new PostPageRequest();
        }
        return new JoinPageRequest();
    }
}
