package http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static webserver.RequestHandler.logger;

public interface ResponseSender {

<<<<<<< HEAD
    public void sendResponse(byte[] response, RequestMessage message, OutputStream out){
        DataOutputStream dos = new DataOutputStream(out);
        sendHeader(dos, message, response.length);
        sendBody(dos, response);
    }

    private void sendHeader(DataOutputStream dos, RequestMessage message, int lengthOfBodyContent) {
        String contenType = message.getRequestLine().getMimeType();
=======
    void sendResponse(byte[] fileToByte, Response response, OutputStream out);

    default void sendHeader(DataOutputStream dos, String message) {

>>>>>>> 9ae34ad (refactor: 인자 이름 수정)
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: "+contenType +"\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
<<<<<<< HEAD

    private void sendBody(DataOutputStream dos, byte[] body) {
=======
    default void sendBody(DataOutputStream dos, byte[] body) {
>>>>>>> 9ae34ad (refactor: 인자 이름 수정)
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
