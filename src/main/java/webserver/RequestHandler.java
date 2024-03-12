package webserver;

import http.HttpRequest;
import http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HeaderPrinter;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    HttpRequest httpRequest;
    HttpResponse httpResponse;
    private Socket connection;

    public RequestHandler(Socket connectionSocket, HttpRequest httpRequest, HttpResponse httpResponse) {
        this.connection = connectionSocket;
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream();
             OutputStream out = connection.getOutputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {

            String[] request = httpRequest.getRequest(br);
            HeaderPrinter.printRequestHeader(br, request[0]);
            httpResponse.sendReponsed(request[1], out);

            //request[0] = requestHeader, request[0]= urlRequest

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
