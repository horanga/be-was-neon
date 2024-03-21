package http.request.message;

import java.util.Collections;
import java.util.List;

public class RequestMessage {
    private RequestLine requestLine;
    private List<String> subsequentLines;

    private String[] userInfo;

    public RequestMessage(){};

    public static RequestMessage getMessage(RequestLine requestLine, List<String> subsequentLines){
        RequestMessage getMessage = new RequestMessage();
        getMessage.requestLine = requestLine;
        getMessage.subsequentLines = subsequentLines;

        return getMessage;
    }

    public static RequestMessage postMessage(RequestLine requestLine, List<String> subsequentLines, String[] userInfo){
        RequestMessage postMessage = new RequestMessage();
        postMessage.requestLine =requestLine;
        postMessage.subsequentLines =subsequentLines;
        postMessage.userInfo = userInfo;

        return postMessage;
    }


    public RequestLine getRequestLine() {
        return requestLine;
    }

    public List<String> getMessage() {
        return Collections.unmodifiableList(subsequentLines);
    }
}
