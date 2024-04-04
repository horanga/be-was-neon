package http.response.header;

import login.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Header {

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

    public String getErrorMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HttpVersion.V_11.getVersion()+statusCode.NOT_FOUND + carriageReturn);
        stringBuilder.append("Date: " + getCurrentTCurrentime() + carriageReturn);
        stringBuilder.append("Content-Type: text/html; charset=UTF-8" + carriageReturn);
        stringBuilder.append("Connection: close" + carriageReturn);
        stringBuilder.append(carriageReturn);
        stringBuilder.append("<html><head><title>404 Not Found</title></head><body>");
        stringBuilder.append("<h1>Not Found</h1>");
        stringBuilder.append("<p>The requested URL was not found on this server.</p>");
        stringBuilder.append("</body></html>" + carriageReturn);
        stringBuilder.append(carriageReturn);

        return stringBuilder.toString();
    }

    private String getCurrentTCurrentime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date());
    }

}
