package http.response.resource;

public class OtherPage implements File {
    @Override
    public java.io.File getFile(String[] resourcePath) {
        String path = joinPath(resourcePath);
        return new java.io.File(RELATIVE_PATH, path);
    }
}
