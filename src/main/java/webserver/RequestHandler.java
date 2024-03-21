package webserver;

import http.request.factory.PathFactories;
import http.request.factory.PathFactory;
import http.request.message.RequestLine;
import http.request.message.RequestMessage;
import http.request.message.parser.GetMethodParser;
import http.request.message.parser.Parser;
import http.request.message.parser.PostMethodParser;
import http.request.path.FilePath;
import http.response.HttpResponse;
import http.response.ResponseSender;
import http.response.factory.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.MessagePrinter;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;

public class RequestHandler implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final Socket connection;
    private final InputStream in;
    private final OutputStream out;
    private Parser parser;

    public RequestHandler(Socket connection, InputStream in, OutputStream out) {
        this.connection = connection;
        this.in = in;
        this.out = out;
    }

    public void run() {
        debugIp();

        try (InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
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
        String requestLine = buffer.readLine();
        if (requestLine.contains("POST")) {
            parser = new PostMethodParser();
        } else {
            parser = new GetMethodParser();
        }
        return parser.parse(requestLine, buffer);
    }

    private void print(RequestMessage Message) {
        MessagePrinter messagePrinter = new MessagePrinter();
        messagePrinter.printRequestHeader(Message);
    }

    private FilePath getContentPath(RequestLine requestLine) throws IOException {
        PathFactory pathFactory = PathFactories.choosePathFactory(requestLine);
        return pathFactory.getFilePath(requestLine.getUri());
    }

    private void respond(FilePath request, RequestMessage message) throws IOException, URISyntaxException {
        ResponseSender sender = new ResponseSender();
        HttpResponse response = ResponseFactory.chooseResponse(request);
        byte[] fileToByte = response.respond(request, message.getRequestLine());
        sender.sendResponse(fileToByte, message, out);
    }

    private void debugIp() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
    }
}