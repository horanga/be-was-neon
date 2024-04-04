package http.response;


import http.request.HttpRequest;
import http.response.header.StatusCode;
import login.LoginManager;

import java.io.File;
import java.io.IOException;

public class Logout implements HttpResponse{

    @Override
    public Response getResponse(File file, HttpRequest httpRequest) throws IOException {
        LoginManager loginManager = new LoginManager();
        loginManager.logout();

        byte[] fileToByte = readFile(file);
        return generateResponse(httpRequest, fileToByte, StatusCode.OK);
    }
}
