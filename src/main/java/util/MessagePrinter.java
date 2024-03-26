package util;

import http.request.message.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

public class MessagePrinter {
    public void printRequestHeader(RequestMessage message) {

        Logger log = LoggerFactory.getLogger(RequestHandler.class);
        log.debug(message.getRequestLine().getRequest());

        message.getMessage().stream().forEach(i -> log.debug(i));
    }
}
