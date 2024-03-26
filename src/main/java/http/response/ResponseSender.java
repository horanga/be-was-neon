package http.response;

import http.request.message.RequestMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static webserver.RequestHandler.logger;

public class ResponseSender {

    public void sendResponse(byte[] file, String headerMessage, OutputStream out) {
        DataOutputStream dos = new DataOutputStream(out);
        sendHeader(dos, headerMessage);
        sendBody(dos, file);
    }

    private void sendHeader(DataOutputStream dos, String message) {

        try {
            dos.writeBytes(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void sendBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
