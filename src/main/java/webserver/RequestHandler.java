package webserver;

import http.factory.MembershipRequestFactory;
import http.factory.PageRequestFactory;
import http.factory.ResponseFactory;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.ResponseSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HeaderPrinter;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;
    private final InputStream in;
    private final OutputStream out;


    public RequestHandler(Socket connection, InputStream in, OutputStream out) {
        this.connection = connection;
        this.in = in;
        this.out = out;
    }

    public void run() {
        debugIp();

        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {

            String requestHeader = br.readLine();
            HeaderPrinter.printRequestHeader(br, requestHeader);
            HttpRequest uri = getRequestType(requestHeader);
            respond(uri, requestHeader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequestType(String requestHeader) throws IOException {
        return requestHeader.contains("?") ?
                new MembershipRequestFactory().getRequest(requestHeader) :
                new PageRequestFactory().getRequest(requestHeader);
    }

    private void respond(HttpRequest requestType, String requestHeader) throws IOException {
        ResponseSender sender = new ResponseSender();
        HttpResponse response = ResponseFactory.getResponse(requestType);
        byte[] fileToByte = response.respondToRequest(requestType, out, requestHeader);
        sender.sendResponse(fileToByte, out);
    }

    private void debugIp() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
    }
}