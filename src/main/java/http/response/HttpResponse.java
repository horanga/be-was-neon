package http.response;

import http.request.HttpRequest;

import java.io.*;

import static webserver.RequestHandler.logger;

public interface HttpResponse {

    public byte[] respondToRequest(HttpRequest httpRequest, OutputStream out, String requstHeader) throws IOException;

    default byte[] getRequestedFile(String urlRequest) throws IOException {
        final String relativePath = "src/main/resources/static/";
        File file = new File(relativePath, urlRequest);

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }

}
