package http.response.header;

public enum HttpVersion {

    V_09("HTTP/0.9"),
    V_10("HTTP/1.0"),
    V_11("HTTP/1.1"),
    V_20("HTTP/2.0"),
    V_30("HTTP/3.0");

    private final String version;

    HttpVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
