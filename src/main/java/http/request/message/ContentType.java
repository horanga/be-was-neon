package http.request.message;

import exception.InvalidContentTypeException;

import java.util.stream.Stream;

public enum ContentType {
    HTML("html", "text/html;charset=utf-8"),
    CSS("css", "text/css;charset=utf-8"),
    JS("js", "application/javascript;charset=utf-8"),
    ICO("ico", "image/x-icon"),
    PNG("png", "image/png"),
    JPG("jpg", "image/jpeg"),
<<<<<<< HEAD
    SVG("svg", "image/svg+xml"),
    NOFile("","" );

=======
    SVG("svg", "image/svg+xml");
>>>>>>> e10f0cf (feat: 지원하는 콘텐츠 타입에 해당하지 않는 예외 추가)
    private final String contentType;
    private final String mimeType;
    ContentType(String contentType, String mimeType) {
        this.contentType = contentType;
        this.mimeType = mimeType;
    }

    public static ContentType getMimeType(String requestLine) {

        return Stream.of(ContentType.values())
                .filter(i -> requestLine.contains(i.contentType))
                .findFirst()
                .orElseThrow(() -> new InvalidContentTypeException("Invalid content type: " + requestLine));
    }
    public String getMimeType() {
        return mimeType;
    }
}
