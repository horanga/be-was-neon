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

public class ResponseManager {

    ResponseSender sender;

    public void respondTo(HttpRequest httpRequest, OutputStream out) throws IOException {
        RequestLine requestLine = httpRequest.getRequestLine();
        String[] uri = httpRequest.getRequestLine().getUri();
        File file = FileType.getFile(requestLine).getFile(uri);

        HttpResponse httpResponse = ResponseType.chooseResponse(httpRequest).getHttpResponse();
        Response response = httpResponse.generateResponse(file, httpRequest);

        if (requestLine.isPostRequest()) {
            sender = new RedirectionSender();
        } else {
            sender = new NormalResponseSender();
        }

        sender.sendResponse(response.getFile(), response, out);
    }
}
