package http.response;

import db.ClientDatabaseImpl;
import http.ClientDatabase;
import http.request.HttpRequest;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;
import login.Cookie;
import login.LoginManager;
import login.SessionManager;
import model.User;

import java.io.File;
import java.io.IOException;

import static http.response.resource.File.RELATIVE_PATH;

public class LoginResult implements HttpResponse {

    public static final String LOGIN_SUCCESS = "main/index.html";
    final String LOGIN_FAIL = "main/login_failed.html";
    Cookie cookie;

    //액션과 계산을 분리
    @Override
    public Response generateResponse(File file, HttpRequest httpRequest) throws IOException {

        String[] loginInfo = httpRequest.getRequestBody().getUserInfo();
        return login(loginInfo) ?
                success(httpRequest, User.loginInfo(loginInfo[0], loginInfo[1]), this) :
                fail(httpRequest);
    }

    public boolean login(String[] loginInfo) throws IOException {
        LoginManager loginManager = new LoginManager();
        return loginManager.login(loginInfo[0], loginInfo[1]); //id와 비밀번호
    }

    Response success(HttpRequest httpRequest, User user, LoginResult response) throws IOException {
        SessionManager sessionManager = new SessionManager();
        sessionManager.createSession(user, response);//액션

        byte[] file = readFile(new File(RELATIVE_PATH, LOGIN_SUCCESS));//액션
        int lengthOfBodyContent = file.length;//액션
        String mimeType = httpRequest.getRequestHeader().getMimeType();//읽기
        Header header
                = Header.redirectHeader(HttpVersion.V_11, mimeType, "http://localhost:8080/index.html");
        new Header(HttpVersion.V_11, StatusCode.OK, mimeType, lengthOfBodyContent);
        return new Response(header, file);
    }

    Response fail(HttpRequest httpRequest) throws IOException {
        File filePath = new File(RELATIVE_PATH, LOGIN_FAIL);
        byte[] file = readFile(filePath);
        int lengthOfBodyContent = file.length;
        String mimeType = httpRequest.getRequestHeader().getMimeType();
        Header header = new Header(HttpVersion.V_11, StatusCode.UNAUTHORIZED, mimeType, lengthOfBodyContent);

        return new Response(header, file);
    }

    public void addCookie(String type,Cookie cookie) {
        ClientDatabase clientDatabase = new ClientDatabaseImpl();
        this.cookie = cookie;
        clientDatabase.addCookie(type, cookie);
    }
}
