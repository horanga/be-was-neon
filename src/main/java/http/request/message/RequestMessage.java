package http.request.message;

import java.util.Collections;
import java.util.List;

public class RequestMessage {
    private final RequestLine requestLine;
    private final List<String> remainsOfMessage;

    public RequestMessage(RequestLine requestLine, List<String> remainsOfMessage) {
        this.requestLine = requestLine;
        this.remainsOfMessage = remainsOfMessage;
    }

    public RequestLine getRequestLine(){
        return requestLine;
    }

    public List<String> getMessage(){
        return Collections.unmodifiableList(remainsOfMessage);
    }
}
