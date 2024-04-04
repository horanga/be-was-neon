package util;

import http.request.HttpRequest;
import http.request.message.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.util.List;

public class MessagePrinter {
    public void printRequest(HttpRequest httpRequest) {

        Logger log = LoggerFactory.getLogger(RequestHandler.class);
        String requestLine = httpRequest.getRequestLine().getRequestLine();
        log.debug(requestLine);

        List<String> requestHeader = httpRequest.getRequestHeader().getRequestHeader();
        requestHeader.stream().forEach(i -> log.debug(i));
    }
}
