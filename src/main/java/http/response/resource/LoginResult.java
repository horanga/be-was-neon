package http.response.resource;

import static http.response.Path.LOGIN_SUCCESS;
import static http.response.Path.RELATIVE_PATH;

public class LoginResult implements File{

    @Override
    public java.io.File getFile(String[] resourcePath) {
        String uri = joinPath(resourcePath);
        String path = uri.replace("login.html", LOGIN_SUCCESS);

        return new java.io.File(RELATIVE_PATH, path);
    }
}
