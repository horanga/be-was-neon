package http.response;

import http.request.HttpRequest;

import java.io.*;

import static webserver.RequestHandler.logger;

public interface HttpResponse {

    public void respondToRequest(HttpRequest httpRequest, OutputStream out, String requstHeader) throws IOException;

    default byte[] getRequestedFile(String urlRequest) throws IOException {
        final String relativePath = "src/main/resources/static/";
        File file = new File(relativePath, urlRequest);

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }

    default void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    default void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
