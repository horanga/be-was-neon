package http.response;

import db.Database;
import http.request.HttpRequest;
import model.User;
import util.Parsor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class JoinResponse implements HttpResponse {
    @Override
    public byte[] respondToRequest(HttpRequest httpRequest, OutputStream out, String requstHeader) throws IOException {
        List<String> strings = Parsor.pasrseJoinRequest(requstHeader);
        Database.addUser(new User(strings.get(0), strings.get(1), strings.get(2), strings.get(3)));
        //userId, password, name, email순으로 저장한다.

        return getRequestedFile(httpRequest.getResourcePath());


    }
}
