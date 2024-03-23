package http.request.message;

import java.util.Collections;
import java.util.List;

public class RequestMessage {

    private String method;
    private RequestLine requestLine;
    //Get ddd
    // 아래줄이 헤더다.

    //리퀘스트랑 리스판스랑 구조동일한가?
    //리퀘스트라인+헤더+바디
    //리스판스와 리퀘스트가 메시지랑 동일하다.
    private List<String> subsequentLines;

    private String[] userInfo;

    public RequestMessage() {
    }

    public static RequestMessage getMessage(RequestLine requestLine, String method, List<String> subsequentLines) {
        RequestMessage getMessage = new RequestMessage();
        getMessage.requestLine = requestLine;
        getMessage.method = method;
        getMessage.subsequentLines = subsequentLines;

        return getMessage;
    }

    public static RequestMessage postMessage(RequestLine requestLine, String method, List<String> subsequentLines, String[] userInfo) {
        RequestMessage postMessage = new RequestMessage();
        postMessage.requestLine = requestLine;
        postMessage.method= method;
        postMessage.subsequentLines = subsequentLines;
        postMessage.userInfo = userInfo;

        return postMessage;
    }


    public RequestLine getRequestLine() {
        return requestLine;
    }

    public List<String> getMessage() {
        return Collections.unmodifiableList(subsequentLines);
    }

    public String[] getUserInfo() {
        return userInfo;
    }

    public String getMethod(){
        return method;
    }
}
