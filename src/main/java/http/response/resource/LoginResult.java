package http.response.resource;

public class LoginResult implements File{

    private final String LOGIN_RESULT_PATH = "main/index.html";
    @Override
    public java.io.File getFile(String[] resourcePath) {
        String uri = joinPath(resourcePath);
        String path = uri.replace("login.html", LOGIN_RESULT_PATH);

        return new java.io.File(RELATIVE_PATH, path);
    }
}
