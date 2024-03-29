package http.response.resource.redirection;

import http.response.resource.File;

public class MemberShip implements File {
    private final String MAIN_PAGE = "index.html";
    @Override
    public java.io.File getFile(String[] resourcePath) {
        return new java.io.File(RELATIVE_PATH, MAIN_PAGE);
    }
}
