package http.request;


import http.request.message.ContentType;
import http.request.message.RequestBody;
import http.request.message.RequestHeader;
import http.request.message.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HttpRequestParser {

    static final Pattern pattern = Pattern.compile("\\b\\w+\s+(.*?)\s+HTTP");

    Pattern loginpattern = Pattern.compile("GET (.+?) HTTP/1.1");

    public HttpRequest parseRequestMessage(InputStream in) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
        BufferedReader socketBuffer = new BufferedReader(inputStreamReader);
        String startLine = socketBuffer.readLine();

        RequestLine requestLine = parseRequestline(startLine);
        RequestHeader requestHeader = parseRequestHeader(socketBuffer, requestLine);
        RequestBody requestBody = null;

        if (requestLine.isPostRequest()) {
            requestBody = parseBody(socketBuffer, requestHeader.getContentSize());
        }
        return new HttpRequest(requestLine, requestHeader, requestBody);
    }

    private RequestLine parseRequestline(String requestLine) {
        String method = parseMethod(requestLine);
        String[] uri = parseUri(requestLine);
        return new RequestLine(requestLine, method, uri);
    }

    private String parseMethod(String requestLine) {
        return requestLine.split(" ")[0].trim();
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

    private RequestHeader parseRequestHeader(BufferedReader buffer, RequestLine requestLine) throws IOException {
        List<String> header = new ArrayList<>();
        int contentSize = parseHeader(buffer, header);
        ContentType mimeType = ContentType.getMimeType(requestLine.getRequestLine());

        return new RequestHeader(header, mimeType.getMimeType(), contentSize);
    }

    private int parseHeader(BufferedReader buffer, List<String> subsequentLines) throws IOException {
        String line;
        int MessageSize = 0;
        while ((line = buffer.readLine()) != null && !line.isEmpty()) {
            if (line.contains("Content-Length")) {//size

                String[] split = line.split(":");
                MessageSize = Integer.parseInt(split[1].trim());
            }

            subsequentLines.add(line);
        }
        return MessageSize;
    }

    private RequestBody parseBody(BufferedReader buffer, int size) throws IOException {
        char[] body = new char[size];

        int bytesRead = buffer.read(body, 0, size);
        if (bytesRead != size) {
            throw new IOException("Content length mismatch.");
        }

        String requestBody = new String(body);
        return new RequestBody(requestBody, parseUserInfo(requestBody));
    }

    private String[] parseUserInfo(String body) {
        String[] split = body.substring(body.indexOf("?") + 1).split("&");
        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).toArray(String[]::new);
    }
}
