package http.request.message;

import java.util.List;

public class RequestHeader {

    private final List<String> requestHeader;
    private final String mimeType;
    private final int contentSize;

    public RequestHeader(List<String> requestHeader, String mimeType, int contentSize) {
        this.requestHeader = requestHeader;
        this.mimeType = mimeType;
        this.contentSize = contentSize;
    }

    public int getContentSize() {
        return contentSize;
    }

    public String getMimeType() {
        return mimeType;
    }
}
