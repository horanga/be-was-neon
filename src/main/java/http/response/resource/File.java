package http.response.resource;

import exception.NonexistentFileException;

public interface File {

    java.io.File getFile(String[] resourcePath) throws NullPointerException;

    default String joinPath(String[] resourcePath) {
        String path = String.join("/", resourcePath);
        return path;
    }

}
