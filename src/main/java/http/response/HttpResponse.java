package http.response;

import db.ClientDatabaseImpl;
import http.ClientDatabase;
import http.request.HttpRequest;
import login.Cookie;
import login.SessionManager;


import java.io.File;
import java.io.IOException;

import static login.SessionManager.LOGIN_SESSION_ID;

public interface HttpResponse {

    Response generateResponse(File file, HttpRequest httpRequest) throws IOException;

    default byte[] readFile(File file) throws IOException {
        StaticFileReader fileReader = new StaticFileReader();
        return fileReader.readFile(file);
    }

    default Cookie getLoginCookie(){
        SessionManager sessionManager = new SessionManager();
        ClientDatabase clientDatabase = new ClientDatabaseImpl();
        return clientDatabase.getCookie(LOGIN_SESSION_ID);

    }

}

