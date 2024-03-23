package http.response;

import db.Database;
import http.request.message.RequestLine;
import http.request.message.RequestMessage;
import http.request.path.FilePath;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;
import model.User;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class MembershipResponse implements HttpResponse {


    @Override
    public Response respond(File files, RequestMessage requestMessage) throws IOException, URISyntaxException {
        String[] userInfo = requestMessage.getUserInfo();
        Database.addUser(new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3]));
        //userId, password, name, email순으로 저장한다.
        LoggerFactory.getLogger("RequestHandler").debug("회원가입이 완료됐습니다.");
        byte[] file = getRequestedFile(files);
        RequestLine requestLine = requestMessage.getRequestLine();

        Header header = Header.redirectHeader(HttpVersion.V_11, requestLine.getMimeType(), "http://localhost:8080/index.html");

        return new Response(header, file);
    }
}
