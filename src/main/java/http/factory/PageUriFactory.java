package http.factory;

import http.request.HttpRequest;
import http.request.JoinPage;
import http.request.PostPage;
import util.Parsor;

public class PageUriFactory implements RequestFactory {
    @Override
    public HttpRequest getRequest(String requestHeader) {
        String request = Parsor.parse(requestHeader);
        if (request.equals("index.html")) {
            return new PostPage();
        }
        return new JoinPage();
    }
}
