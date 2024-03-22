package http.request.path.html;

import http.request.path.FilePath;

public class Membership implements FilePath {

    public String getResourcePath(){
        return "main/index.html";
        //요청 파일x 단순히 회원가입
        //회원가입을 하면 "main/index.html"화면이 뜨도록 설정
    }
}
