package http.request;

import java.io.IOException;

public class PostPage implements HttpRequest {

    public String getUri() throws IOException {
        return "index.html";
        //resource 폴더에 들어가면 바로 포스팅이 있는 화면 http파일인 index.html가 있음
    }
}
