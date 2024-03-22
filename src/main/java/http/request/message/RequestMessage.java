package http.request.message;

import java.util.Collections;
import java.util.List;

public class RequestMessage {
    private final RequestLine requestLine;
    private final List<String> subsequentLines;

    public RequestMessage(RequestLine requestLine, List<String> subsequentLines) {
        this.requestLine = requestLine;
        this.subsequentLines = subsequentLines;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public List<String> getMessage() {
        return Collections.unmodifiableList(subsequentLines);
    }
}
