package http.response;

import http.request.HttpRequest;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static http.response.Path.RELATIVE_PATH;

public interface HttpResponse {

    Response getResponse(File file, HttpRequest httpRequest) throws IOException;

    default byte[] readFile(File file) throws IOException {
        StaticFileReader fileReader = new StaticFileReader();
        return fileReader.readFile(file);
    }
    default Response generateResponse(HttpRequest httpRequest, byte[] files, StatusCode code) throws IOException {
        String mimeType = httpRequest.getMimeType();
        int lengthOfBodyContent = files.length;
        Header header = new Header(HttpVersion.V_11, code, mimeType, lengthOfBodyContent);

        return new Response(header, files);
    }

    default Response generateRedirectResponse(HttpRequest httpRequest, byte[] files, String location) throws IOException {
        String mimeType = httpRequest.getMimeType();
        Header header = Header.redirectHeader(HttpVersion.V_11, mimeType, location);

        return new Response(header, files);
    }
}

