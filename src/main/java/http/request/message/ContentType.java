package http.request.message;

import java.util.stream.Stream;

public enum ContentType {

    HTML("html", "text/html;charset=utf-8"),
    CSS("css", "text/css;charset=utf-8"),
    JS("js", "application/javascript;charset=utf-8"),
    ICO("ico", "image/x-icon"),
    PNG("png", "image/png"),
    JPG("jpg", "image/jpeg"),
    SVG("svg", "image/svg+xml");

    private final String contentType;
    private final String mimeType;

    ContentType(String contentType, String mimeType) {
        this.contentType = contentType;
        this.mimeType = mimeType;
    }

    public static ContentType getMimeType(String requestLine) {

        ContentType defaultType = HTML;

        return Stream.of(ContentType.values())
                .filter(i -> requestLine.contains(i.contentType))
                .findFirst()
                .orElse(defaultType);
    }

    public String getMimeType() {
        return mimeType;
    }
}
