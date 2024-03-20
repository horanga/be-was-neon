package http.response;

import http.request.path.FilePath;
import http.request.message.RequestLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public interface HttpResponse {
    public byte[] respond(FilePath httpRequest, RequestLine requestLine) throws IOException, URISyntaxException;

    static byte[] getRequestedFile(java.io.File file) throws IOException {

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
