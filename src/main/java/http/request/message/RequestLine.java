package http.request.message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestLine {

    private String requestLine;
    private String[] uri;
    private String mimeType;

    public static RequestLine getRequest(String requestLine, String[] uri, String mimeType){
        RequestLine getRequest = new RequestLine();
        getRequest.requestLine=requestLine;
        getRequest.uri=uri;
        getRequest.mimeType=mimeType;
        return getRequest;
    }

    public static RequestLine postRequest(String requestLine){
        RequestLine postRequest = new RequestLine();
        postRequest.requestLine = requestLine;
        postRequest.uri = new String[]{"/register"};

        return postRequest;
    }

    public boolean hasMatchingPath(List<String> uriList) {
        return uriList.stream().anyMatch(i-> Arrays.stream(uri).)
                Arrays.stream(uri).anyMatch(i->uriList)
                || uriList.stream().anyMatch(requestLine::equals);
    }

    public List<String> getUserInfo() {
        String[] split = requestLine.substring(requestLine.indexOf("?") + 1).split("&");

        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).collect(Collectors.toList());
    }

    public String[] getUri() {
        return uri;
    }

    public String getRequest() {
        return requestLine;
    }

    public String getMimeType() {
        return mimeType;
    }
}
