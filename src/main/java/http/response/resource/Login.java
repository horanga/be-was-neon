package http.response.resource;

import static http.response.Path.LOGIN_PAGE;
import static http.response.Path.RELATIVE_PATH;

public class Login implements File {

    @Override
    public java.io.File getFile(String[] resourcePath) {
        String uri = joinPath(resourcePath);
        //static/login 폴더 구조로 돼 있어서 user로 들어오는 경로값을 login으로 변경

        return new java.io.File(RELATIVE_PATH, LOGIN_PAGE);
    }
}
