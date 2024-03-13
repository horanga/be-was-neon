package http.response;

import java.io.*;

import static webserver.RequestHandler.logger;

public class PageResponse implements HttpResponse {
    @Override
    public void respondToRequest(String urlRequest, OutputStream out) throws IOException {
        byte[] response = getRequestedFile(urlRequest);
        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, response.length);
        responseBody(dos, response);
    }

    private byte[] getRequestedFile(String urlRequest) throws IOException {
        final String relativePath = "src/main/resources/static/";
        File file = new File(relativePath, urlRequest);

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }

        return data;
    }

}
