package http.response;

import http.ClientDatabase;
import http.UserDatabase;
import http.dynamichttp.DynamicHttpGenerator;
import http.request.HttpRequest;
import http.response.header.StatusCode;
import login.Cookie;
import login.LoginManager;
import login.SessionManager;
import model.User;

import java.io.File;
import java.io.IOException;

import static http.response.Path.*;

public class LoginResult implements HttpResponse {
    Cookie cookie;
    ClientDatabase clientDatabase;
    UserDatabase userDatabase;

    public LoginResult(ClientDatabase clientDatabase, UserDatabase userDatabase) {
        this.clientDatabase = clientDatabase;
        this.userDatabase = userDatabase;
    }

    @Override
    public Response getResponse(File file, HttpRequest httpRequest) throws IOException {

        String[] loginInfo = httpRequest.getUserInfo();

        return login(loginInfo[0], loginInfo[1]) ? //id와 비밀번호
                success(httpRequest, User.loginInfo(loginInfo[0], loginInfo[1]), this) :
                fail(httpRequest);
    }

    boolean login(String id, String password){
        return new LoginManager().login(id, password);

    }

    Response success(HttpRequest httpRequest, User user, LoginResult response) throws IOException {
        SessionManager sessionManager = new SessionManager();
        sessionManager.createSession(user, response);
        User userInfo = getUserInfo(httpRequest, user, sessionManager);
        return generateSuccessResponse(httpRequest, userInfo);
    }

    Response fail(HttpRequest httpRequest) throws IOException {
        byte[] fileToByte = readFile(new File(RELATIVE_PATH, LOGIN_FAIL));
        return generateResponse(httpRequest, fileToByte, StatusCode.UNAUTHORIZED);
    }

    public void addCookie(String type, Cookie cookie) {
        this.cookie = cookie;
        clientDatabase.addCookie(type, cookie);
    }

    private User getUserInfo(HttpRequest httpRequest, User user, SessionManager sessionManager) {
        //유저가 가입하고 첫 로그인할 때 httpRequest에 아직 쿠키가 전달이 안 됨.
        //쿠키에서 sid를 가져올 수 없기 때문에 유저 DB에서 정보를 가져와서 첫 로그인 후 이용자 이름을 표시해줌

        return sessionManager.isSessionExisted()?
                getUserInfoFromSid(httpRequest, sessionManager):
                getUserInfoFromDb(user);
    }

    private User getUserInfoFromSid(HttpRequest httpRequest, SessionManager sessionManager) {
        User userInfo;
        String sid = httpRequest.getSid();
        userInfo = sessionManager.getUserInfo(sid);
        return userInfo;
    }

    private User getUserInfoFromDb(User user) {
        return userDatabase.findUserById(user.getUserId()).get();
    }
    private Response generateSuccessResponse(HttpRequest httpRequest, User userInfo) throws IOException {
        byte[] modifiedFile = generateDynamicHtmlWithUserInfo(userInfo);

        return generateRedirectResponse(httpRequest, modifiedFile,"http://localhost:8080/index.html");
    }

    private byte[] generateDynamicHtmlWithUserInfo(User userInfo) throws IOException {
        DynamicHttpGenerator generator = new DynamicHttpGenerator();
        byte[] fileToByte = readFile(new File(RELATIVE_PATH, LOGIN_SUCCESS));
        return generator.generateHtmlForUserId(fileToByte, userInfo);
    }

}
