package http.factory;

import http.request.HttpRequest;

public interface RequestFactory {

    HttpRequest getRequest(String request);

}
