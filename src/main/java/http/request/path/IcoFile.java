package http.request.path;

public class IcoFile implements FilePath {
    @Override
    public String getResourcePath() {
        return "favicon.ico";
    }
}
