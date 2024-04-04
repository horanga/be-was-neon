package http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static webserver.RequestHandler.logger;

public interface ResponseSender {

    void sendResponse(byte[] fileToByte, Response response, OutputStream out);

    default void sendHeader(DataOutputStream dos, String message) {

        try {
            dos.writeBytes(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    default void sendBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
