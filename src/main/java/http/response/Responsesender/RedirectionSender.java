package http.response.Responsesender;

import http.response.Response;
import http.response.ResponseSender;
import http.response.header.Header;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class RedirectionSender implements ResponseSender {
    @Override
    public void sendResponse(byte[] fileToByte, Response response, OutputStream out) {

        DataOutputStream dos = new DataOutputStream(out);
        Header header = response.getHeader();
        String redirectionMessage = header.getRedirectionMessage();
        sendHeader(dos, redirectionMessage);
        sendBody(dos, fileToByte);

    }
}
