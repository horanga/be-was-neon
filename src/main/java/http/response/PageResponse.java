package http.response;

import http.request.message.RequestMessage;
import http.request.path.FilePath;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;

import java.io.*;
import java.net.URISyntaxException;

import static webserver.RequestHandler.logger;

public class PageResponse implements HttpResponse {
    @Override
    public Response respond(File files, RequestMessage requestMessage) throws IOException, URISyntaxException {
        byte[] file = getRequestedFile(files);
        String mimeType = requestMessage.getRequestLine().getMimeType();
        int lengthOfBodyContent = file.length;
        Header header = new Header(HttpVersion.V_11, StatusCode.OK, mimeType, lengthOfBodyContent);

        return new Response(header, file);
    }

}
