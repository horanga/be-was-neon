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
    private final String process;

    ContentType(String contentType, String process) {
        this.contentType = contentType;
        this.process = process;
    }

    public static String getContentType(String requestLine) {
        return Stream.of(ContentType.values())
                .filter(i -> requestLine.contains(i.contentType))
                .findFirst()
                .map(i -> i.process)
                .get();
    }

}
