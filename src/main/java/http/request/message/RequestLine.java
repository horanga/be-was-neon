package http.request.message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestLine {

    private final String requestLine;
    private final String[] uri;
    private final String mimeType;


    public RequestLine(String requestLine, String[] uri, String mimeType) {
        this.requestLine = requestLine;
        this.uri = uri;
        this.mimeType = mimeType;
    }

    public boolean hasMatchingPath(List<String> uriList) {
        return Arrays.stream(uri).anyMatch(uriList::contains)
                || uriList.stream().anyMatch(requestLine::contains);
    }

    public List<String> getUserInfo() {
        String[] split = requestLine.substring(requestLine.indexOf("?") + 1).split("&");

        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).collect(Collectors.toList());
    }

    public String[] getUri() {
        return uri;
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getMimeType() {
        return mimeType;
    }
}
