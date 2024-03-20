package http.response;

import db.Database;
import http.request.HttpRequest;
import model.User;
import util.Parsor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class JoinResponse implements HttpResponse{
    @Override
    public void respondToRequest(HttpRequest httpRequest, OutputStream out, String requstHeader) throws IOException {
        byte[] response = getRequestedFile(httpRequest.getUri());
        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, response.length);
        responseBody(dos, response);

        List<String> strings = Parsor.pasrseJoinRequest(requstHeader);
        Database.addUser(new User(strings.get(0),strings.get(1), strings.get(2), strings.get(3) ));
        //userId, password, name, email순으로 저장한다.
    }
}
