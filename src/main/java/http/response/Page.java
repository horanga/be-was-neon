package http.response;

import db.ClientDatabaseImpl;
import http.ClientDatabase;
import http.request.HttpRequest;
import http.request.message.ContentType;
import http.response.header.StatusCode;
import login.Cookie;

import java.io.*;
import static http.response.Path.LOGIN_SUCCESS;
import static http.response.Path.RELATIVE_PATH;
import static login.SessionManager.LOGIN_SESSION_ID;

public class Page implements HttpResponse {
    @Override
    public Response getResponse(File file, HttpRequest httpRequest) throws IOException {
        Cookie loginCookie = getLoginCookie();


        return loginCookie != null
                && httpRequest.getMimeType().equals(ContentType.HTML.getMimeType())?
            //로그인 쿠키가 존재함
           generateResponse(httpRequest, readFile(new File(RELATIVE_PATH, LOGIN_SUCCESS)), StatusCode.OK):
           generateResponse(httpRequest, readFile(file), StatusCode.OK);

    }

     private Cookie getLoginCookie(){
        ClientDatabase clientDatabase = new ClientDatabaseImpl();
        return clientDatabase.getCookie(LOGIN_SESSION_ID);

    }
}
