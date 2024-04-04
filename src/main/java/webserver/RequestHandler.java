package webserver;

<<<<<<< HEAD
import http.request.factory.PathFactories;
import http.request.path.FilePath;
import http.request.message.MessageParser;
import http.request.message.RequestLine;
import http.request.message.RequestMessage;
import http.response.HttpResponse;
import http.response.ResponseSender;
import http.response.factory.ResponseFactory;
=======
import exception.*;
import http.request.HttpRequest;
import http.request.HttpRequestParser;
import http.request.HttpRequestValidator;
import http.response.ErrorManager;
import http.response.ResponseManager;
import org.jetbrains.annotations.NotNull;
>>>>>>> a2aa51d (refactor: 404 에러 추가)
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

<<<<<<< HEAD

        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader socketBuffer = new BufferedReader(inputStreamReader)) {

            RequestMessage requestMessage = parse(socketBuffer);
            RequestLine requestLine = requestMessage.getRequestLine();
            FilePath path = getContentPath(requestLine);
            print(requestMessage);
            respond(path, requestMessage);


        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private RequestMessage parse(BufferedReader buffer) throws IOException {
        MessageParser messageParser = new MessageParser();
        return messageParser.parseRequestMessage(buffer);
    }

    private void print(RequestMessage Message) {
        MessagePrinter messagePrinter = new MessagePrinter();
        messagePrinter.printRequestHeader(Message);
    }

    private FilePath getContentPath(RequestLine requestLine) throws IOException {
        return PathFactories.getPath(requestLine.getUri());
    }

    private void respond(FilePath request, RequestMessage message) throws IOException, URISyntaxException {
        ResponseSender sender = new ResponseSender();
        HttpResponse response = ResponseFactory.chooseResponse(request);
        byte[] fileToByte = response.respond(request, message.getRequestLine());
        sender.sendResponse(fileToByte, message, out);
    }
  
    private void debugIp() {
=======
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
>>>>>>> a2aa51d (refactor: 404 에러 추가)
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