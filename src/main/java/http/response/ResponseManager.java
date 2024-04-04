package http.response;

import http.request.HttpRequest;
import http.request.message.RequestLine;
import http.response.Responsesender.NormalResponseSender;
import http.response.Responsesender.RedirectionSender;
import http.response.factory.ResponseType;
import http.response.resource.FileType;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static http.response.Path.NOT_FOUND_ERROR;
import static http.response.Path.RELATIVE_PATH;

public class ResponseManager {

    ResponseSender sender;

    public void respondTo(HttpRequest httpRequest, OutputStream out) throws IOException {
        RequestLine requestLine = httpRequest.getRequestLine();
        File file = getFile(httpRequest, requestLine);
        Response response = getResponse(httpRequest, file);
        chooseSenderType(requestLine);
        sender.sendResponse(response.getFileAsByte(), response, out);
    }
    private void chooseSenderType(RequestLine requestLine) {
        if (requestLine.isPostRequest()) {
            sender = new RedirectionSender();
        } else {
            sender = new NormalResponseSender();
        }
    }

    private Response getResponse(HttpRequest httpRequest, File file) throws IOException {
        HttpResponse httpResponse = ResponseType.chooseResponse(httpRequest).
                getHttpResponse();
        return httpResponse.getResponse(file, httpRequest);
    }

    private File getFile(HttpRequest httpRequest, RequestLine requestLine) {
        String[] uri = httpRequest.getUri();
        return  FileType.getFile(requestLine).
                getFile(uri);
    }
}
