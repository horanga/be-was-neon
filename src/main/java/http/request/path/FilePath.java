package http.request.path;

import java.io.File;

public interface FilePath {
    final String relativePath = "src/main/resources/static/";

    File getFile(String[] resourcePath);

    default String joinUri(String[] resourcePath) {
        String path = String.join("/", resourcePath);
        return path;

    }
}
