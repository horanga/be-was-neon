package http.request.message.parser;

import http.request.message.RequestLine;
import http.request.message.RequestMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostMethodParser implements Parser {
    @Override
    public RequestMessage parse(String requestLine, BufferedReader buffer) throws IOException {
        List<String> subsequentHeader = new ArrayList<>();
        int messageSize = getSubsequentLines(buffer, subsequentHeader);
        String body = "";
        if (messageSize > 0) {
            body = parseBody(buffer, messageSize);
        }

        String[] userInfo = parseMembershipRequest(body);
        return RequestMessage.postMessage(RequestLine.postRequest(requestLine), subsequentHeader, userInfo);
    }

    //한글로 하면 안되는 거 수정하기

    private String[] parseMembershipRequest(String request) {
        String[] split = request.substring(request.indexOf("?") + 1).split("&");
        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).toArray(String[]::new);
    }

    private String parseBody(BufferedReader buffer, int size) throws IOException {
        char[] body = new char[size];
        //StringBuilder-->문장을 붙여주는 거
        int bytesRead = buffer.read(body, 0, size);
        if (bytesRead != size) {
            throw new IOException("Content length mismatch.");
        }
        return new String(body);
    }
}
