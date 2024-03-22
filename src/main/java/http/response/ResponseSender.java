package http.response;

import http.request.message.RequestMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static webserver.RequestHandler.logger;

public class ResponseSender {

    public void sendResponse(byte[] response, RequestMessage message, OutputStream out){
        DataOutputStream dos = new DataOutputStream(out);
        sendHeader(dos, message, response.length);
        sendBody(dos, response);
    }

    private void sendHeader(DataOutputStream dos, RequestMessage message, int lengthOfBodyContent) {
        String contenType = message.getRequestLine().getMimeType();
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: "+contenType +"\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
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
