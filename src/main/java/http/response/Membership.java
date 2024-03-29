package http.response;

import http.Database;
import http.request.HttpRequest;
import http.request.message.RequestLine;
import http.response.header.Header;
import http.response.header.HttpVersion;
import model.User;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Membership implements HttpResponse {

    Database database;

    public Membership(Database database) {
        this.database = database;
    }

    @Override
    public Response generateResponse(File file, HttpRequest httpRequest) throws IOException{
        String[] userInfo = httpRequest.getRequestBody().getUserInfo();
        database.addUser(new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3]));
        //userId, password, name, email순으로 저장한다.
        LoggerFactory.getLogger("RequestHandler").debug("회원가입이 완료됐습니다.");
        byte[] files = readFile(file);
        String mimeType = httpRequest.getRequestHeader().getMimeType();

        Header header = Header.redirectHeader(HttpVersion.V_11, mimeType, "http://localhost:8080/index.html");
        return new Response(header, files);
    }
}
