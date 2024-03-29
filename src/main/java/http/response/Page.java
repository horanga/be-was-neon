package http.response;

import http.request.HttpRequest;
import http.request.message.ContentType;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;
import login.Cookie;
import model.User;

import java.io.*;

import static http.response.LoginResult.LOGIN_SUCCESS;
import static http.response.resource.File.RELATIVE_PATH;

public class Page implements HttpResponse {
    @Override
    public Response generateResponse(File file, HttpRequest httpRequest) throws IOException {
        Cookie loginCookie = getLoginCookie();
        if (loginCookie != null && httpRequest.getRequestHeader().getMimeType() == ContentType.HTML.getMimeType()) { //로그인 쿠키가 존재함
            byte[] files = readFile(new File(RELATIVE_PATH, LOGIN_SUCCESS));
            String mimeType = ContentType.HTML.getMimeType();
            int lengthOfBodyContent = files.length;
            Header header
                    = new Header(HttpVersion.V_11, StatusCode.OK, mimeType, lengthOfBodyContent);

            return new Response(header, files);
        } else {

            byte[] files = readFile(file);
            String mimeType = httpRequest.getRequestHeader().getMimeType();
            int lengthOfBodyContent = files.length;
            Header header = new Header(HttpVersion.V_11, StatusCode.OK, mimeType, lengthOfBodyContent);

            return new Response(header, files);
        }
    }
}
