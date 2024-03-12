package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;

public class HeaderPrinter {
    public static void printRequestHeader(String httpRequestHeader, BufferedReader br) throws IOException {
        Logger log = LoggerFactory.getLogger(RequestHandler.class);
        log.debug(httpRequestHeader);

        while (!httpRequestHeader.isEmpty()) {
            httpRequestHeader = br.readLine();
           log.debug(httpRequestHeader);
        }
    }
}
