package http.request.message.parser;

import http.request.message.ContentType;
import http.request.message.RequestLine;
import http.request.message.RequestMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class GetMethodParser implements Parser {


    private static final Pattern pattern = Pattern.compile("GET\s+(.*?)\s+HTTP");

    @Override
    public RequestMessage parse(String requestLine, BufferedReader buffer) throws IOException {
        String mimeType = ContentType.getMimeType(requestLine);
        List<String> subsequentHeader = new ArrayList<>();
        getSubsequentLines(buffer, subsequentHeader);
        return RequestMessage
                .getMessage(RequestLine.getRequest(requestLine, parseUri(requestLine), mimeType)
                        ,getMethod(requestLine), subsequentHeader);
    }

    private String[] parseUri(String input) {
        Matcher matcher = pattern.matcher(input);
        String path = "";
        if (matcher.find()) {
            path = matcher.group(1);
        }
        return Stream.of(path.split(" ")).filter(i -> !i.isEmpty())
                .toArray(String[]::new);
    }


}
