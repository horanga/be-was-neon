package http.request;

import java.io.IOException;

public class MembershipRequest implements HttpRequest {


    @Override
    public String getResourcePath() throws IOException {
        return "registration/index.html";

        //회원가입 폼을 제출하는 uri임을 식별하하도록 도와주는 문자열
    }
}
