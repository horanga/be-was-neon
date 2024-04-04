package http.response.Responsesender;

import http.response.Response;
import http.response.ResponseSender;
import http.response.header.Header;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class NormalResponseSender implements ResponseSender {
    @Override
    public void sendResponse(byte[] fileToByte, Response response, OutputStream out) {

        DataOutputStream dos = new DataOutputStream(out);
        Header header = response.getHeader();
        String message = header.getMessage();
        sendHeader(dos, message);
        sendBody(dos, fileToByte);
    }
}
