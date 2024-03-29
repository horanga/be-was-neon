package http.response.header;

import http.ClientDatabase;
import login.SessionManager;

public class Header {
    //리퀘스트랑 리스판스랑 헤더가 같다.

    private  String carriageReturn = "\r\n";
    private  HttpVersion httpVersion;
    private  StatusCode statusCode;
    private  String contentType;
    private  int contentLength;
    private String newLocation;
    SessionManager sessionManager = new SessionManager();
    public Header(){};


    public Header(HttpVersion httpVersion, StatusCode statusCode, String contentType, int contentLength) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    public static Header redirectHeader(HttpVersion httpVersion, String contentType, String newLocation) {

        Header header = new Header();
        header.httpVersion = httpVersion;
        header.statusCode = StatusCode.REDIRECT;
        header.contentType = contentType;
        header.newLocation = newLocation;

        return header;
    }

    public String getLoginSuccesMessage(String sid){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMessage());
        stringBuilder.append("Set-Cookie: sid=" + sid+";"+" Path="+carriageReturn);
        stringBuilder.append(carriageReturn);

        return stringBuilder.toString();
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpVersion.getVersion() + " " + statusCode.getMessage() + carriageReturn);
        stringBuilder.append("Content-Type: " + contentType + carriageReturn);
        stringBuilder.append("Content-Length: " + contentLength + carriageReturn);
        stringBuilder.append(carriageReturn);

        return stringBuilder.toString();
    }

    public String getRedirectionMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpVersion.getVersion()+statusCode.getMessage()+carriageReturn);
        stringBuilder.append("Location: "+newLocation+carriageReturn);
        stringBuilder.append("Content-Type: " + contentType + carriageReturn);
        stringBuilder.append(""+carriageReturn);
        stringBuilder.append("<html><body>"+carriageReturn);
        stringBuilder.append("Redirecting to <a href=\""+ newLocation + "\">" + newLocation + "</a>"+carriageReturn);
        stringBuilder.append("</body></html>"+carriageReturn);
        stringBuilder.append(carriageReturn);
        return stringBuilder.toString();
    }
}
