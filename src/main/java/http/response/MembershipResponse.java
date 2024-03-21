package http.response;

import db.Database;
import http.request.path.FilePath;
import http.request.message.RequestLine;
import model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

public class MembershipResponse implements HttpResponse {


    @Override
    public byte[] respond(FilePath httpRequest, RequestLine requestLine) throws IOException, URISyntaxException {
        List<String> userInfo = requestLine.getUserInfo();
        Database.addUser(new User(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3)));
        //userId, password, name, email순으로 저장한다.
        Logger.getLogger("RequestHandler").info("회원가입이 완료됐습니다.");

        return HttpResponse.getRequestedFile(httpRequest.getFile());
    }
}
