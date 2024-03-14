package http.request;

import java.io.IOException;

public class JoinPageRequest implements HttpRequest {
    @Override
    public String getResourcePath() throws IOException {

        return "registration/index.html";
        //resource 폴더 내에서 registration 폴더에 회원가입을 위한 index.html 파일이 있음
    }
}
