package webserver;

import exception.*;
import http.request.HttpRequest;
import http.request.HttpRequestParser;
import http.request.HttpRequestValidator;
import http.response.ErrorManager;
import http.response.ResponseManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        ErrorManager errorManager = new ErrorManager();

        try {
            executeSafely(respondToHttpRequest(handler));
        } catch (InvalidContentTypeException | InvalidHttpMethodException
                 | InvalidPostRequestException | NonexistentFileException e) {
            executeSafely(handleError(errorManager));
        }finally {
            closeConnection();
        }
    }

    @NotNull
    private Action handleError(ErrorManager errorManager) {
        return () -> errorManager.respondToError(out);
    }

    @NotNull
    private Action respondToHttpRequest(ResponseManager handler) {
        return () -> {
            HttpRequest httpRequest = convertToHttpRequest(in);
            handler.respondTo(httpRequest, out);
        };
    }

    public void executeSafely(Action action){
        try {
            action.excute();
        }catch (IOException e){
        }
    }

    private HttpRequest convertToHttpRequest(InputStream in) throws IOException {
        HttpRequestParser parser = new HttpRequestParser();
        HttpRequest httpRequest = parser.parseRequestMessage(in);
        HttpRequestValidator validator = new HttpRequestValidator();
        validator.validate(httpRequest);
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