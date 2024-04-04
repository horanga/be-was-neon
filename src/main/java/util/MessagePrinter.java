package util;

import http.request.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

public class MessagePrinter {
    public void printRequestHeader(HttpRequest request) {

        Logger log = LoggerFactory.getLogger(RequestHandler.class);
        log.debug(request.getRequestLine().getRequestLine());

        request.getRequestHeader().getRequestHeader().stream().forEach(i -> log.debug(i));
    }
}
