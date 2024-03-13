package webserver;

import http.factory.JoinUriFactory;
import http.factory.PageUriFactory;
import http.factory.ResponseFactory;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HeaderPrinter;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        debugIp();

        try (InputStream in = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(in);
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
                new JoinUriFactory().getRequest(requestHeader) :
                new PageUriFactory().getRequest(requestHeader);
    }

    private void respond(HttpRequest requestType, String requestHeader) {
        HttpResponse response = ResponseFactory.getResponse(requestType);
        try (OutputStream out = connection.getOutputStream()) {
            response.respondToRequest(requestType, out, requestHeader);
        } catch (IOException e) {
            logger.debug(e.getMessage());

        }
    }

    private void debugIp() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
    }
}