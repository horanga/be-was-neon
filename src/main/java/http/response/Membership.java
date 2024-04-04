package http.response;

import http.UserDatabase;
import http.request.HttpRequest;
import model.User;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Membership implements HttpResponse {

    UserDatabase userDatabase;

    public Membership(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public Response getResponse(File file, HttpRequest httpRequest) throws IOException{
        String[] userInfo = httpRequest.getRequestBody().getUserInfo();
        userDatabase.addUser(new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3]));
        //userId, password, name, email순으로 저장한다.
        LoggerFactory.getLogger("RequestHandler").debug("회원가입이 완료됐습니다.");
        byte[] fileToBytes = readFile(file);

        return generateRedirectResponse(httpRequest, fileToBytes,"http://localhost:8080/index.html");

    }
}
