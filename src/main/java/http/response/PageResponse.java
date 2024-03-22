package http.response;



import http.request.path.FilePath;
import http.request.message.RequestLine;

import java.io.*;
import java.net.URISyntaxException;

public class PageResponse implements HttpResponse {
    @Override
    public byte[] respond(FilePath httpRequest, RequestLine requestLine) throws IOException, URISyntaxException {
        return HttpResponse.getRequestedFile(httpRequest.getFile());
    }
}
