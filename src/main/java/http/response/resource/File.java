package http.response.resource;

public interface File {
    public static final String RELATIVE_PATH = "src/main/resources/static/";

    java.io.File getFile(String[] resourcePath);

    default String joinPath(String[] resourcePath) {
        String path = String.join("/", resourcePath);
        return path;
    }
}
