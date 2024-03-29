package webserver;

import http.request.HttpRequest;
import http.request.HttpRequestParser;
import http.response.ResponseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logIp();
        ResponseManager handler = new ResponseManager();

        try {
            HttpRequest httpRequest = convertToHttpRequest(in);
            handler.respondTo(httpRequest, out);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            closeConnection();
        }
    }


    private HttpRequest convertToHttpRequest(InputStream in) throws IOException {
        HttpRequestParser parser = new HttpRequestParser();
        HttpRequest httpRequest = parser.parseRequestMessage(in);

        return httpRequest;
    }

    private void logIp() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection closed.");
            } catch (IOException e) {
                logger.error("Failed to close the connection: ", e);
            }
        }
    }
}