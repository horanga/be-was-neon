package http.response;

import http.request.HttpRequest;

import java.io.*;

import static webserver.RequestHandler.logger;

public class PageResponse implements HttpResponse {
    @Override
    public void respondToRequest(HttpRequest httpRequest, OutputStream out, String requestHeader) throws IOException {
        byte[] response = getRequestedFile(httpRequest.getUri());
        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, response.length);
        responseBody(dos, response);
    }
}
