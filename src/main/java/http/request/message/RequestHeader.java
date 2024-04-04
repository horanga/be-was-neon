package http.request.message;

import java.util.Collections;
import java.util.List;

public class RequestHeader {

    private final List<String> requestHeader;
    private final ContentType contentType;
    private final int contentSize;

    public RequestHeader(List<String> requestHeader, ContentType contentType, int contentSize) {
        this.requestHeader = requestHeader;
        this.contentType = contentType;
        this.contentSize = contentSize;
    }

    public int getContentSize() {
        return contentSize;
    }

    public String getMimeType() {
        return contentType.getMimeType();
    }

    public List<String> getRequestHeader() {
        return Collections.unmodifiableList(requestHeader);
    }


}
