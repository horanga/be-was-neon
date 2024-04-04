package http.response.resource;

import static http.response.Path.MAIN_PAGE;
import static http.response.Path.RELATIVE_PATH;

public class Logout implements File{
    @Override
    public java.io.File getFile(String[] resourcePath) {
        return new java.io.File(RELATIVE_PATH, MAIN_PAGE);
    }
}
