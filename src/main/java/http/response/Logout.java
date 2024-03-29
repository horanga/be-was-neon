package http.response;


import http.request.HttpRequest;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;
import login.LoginManager;
import java.io.File;
import java.io.IOException;

import static http.response.resource.File.RELATIVE_PATH;
import static http.response.resource.redirection.MemberShip.MAIN_PAGE;

public class Logout implements HttpResponse{
    @Override
    public Response generateResponse(File file, HttpRequest httpRequest) throws IOException {
        LoginManager loginManager = new LoginManager();
        loginManager.logout();

        byte[] files = readFile(new File(RELATIVE_PATH, MAIN_PAGE));
        String mimeType = httpRequest.getRequestHeader().getMimeType();
        int lengthOfBodyContent = files.length;
        Header header = new Header(HttpVersion.V_11, StatusCode.OK, mimeType, lengthOfBodyContent);

        return new Response(header, files);
    }
}
