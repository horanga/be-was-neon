package http.response;

import http.request.HttpRequest;

import java.io.*;

public class PageResponse implements HttpResponse {
    @Override
    public byte[] respondToRequest(HttpRequest httpRequest, OutputStream out, String requestHeader) throws IOException {
        return getRequestedFile(httpRequest.getResourcePath());

    }
}
