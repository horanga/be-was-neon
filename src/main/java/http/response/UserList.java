package http.response;

import http.UserDatabase;
import http.dynamichttp.DynamicHttpGenerator;
import http.request.HttpRequest;
import http.response.header.StatusCode;
import login.SessionManager;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static http.response.Path.LOGIN_PAGE;
import static http.response.Path.RELATIVE_PATH;

public class UserList implements HttpResponse {

    UserDatabase userDatabase;

    public UserList(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public Response getResponse(File file, HttpRequest httpRequest) throws IOException {

        SessionManager sessionManager = new SessionManager();
        if(sessionManager.isSessionExisted()) {

            DynamicHttpGenerator dynamicHttpGenerator = new DynamicHttpGenerator();
            Collection<User> all = userDatabase.findAll();

            byte[] fileToByte = readFile(file);
            byte[] modifiedFiles = dynamicHttpGenerator.generateHtmlForUserList(fileToByte, all);
            return generateResponse(httpRequest, modifiedFiles, StatusCode.OK);

        } else {

            byte[] fileTobyte = readFile(new File(RELATIVE_PATH, LOGIN_PAGE));
            return generateResponse(httpRequest, fileTobyte, StatusCode.UNAUTHORIZED);
        }


    }
}
