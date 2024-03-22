package http.request.path.html;

import http.request.path.FilePath;

public class PostPage implements FilePath {

    public String getResourcePath(){
        return "index.html";
        //http://localhost:8080/index.html를 주소창에 입력하면 index.html파일이 화면이 뜬다.
    }
}
