package http;

import java.io.BufferedReader;
import java.io.IOException;

public class PostRequest implements HttpRequest {

    public String getRequest() throws IOException {
        return "index.html";
        //resource 폴더에 들어가면 바로 포스팅이 있는 화면 http파일인 index.html가 있음
    }
}
