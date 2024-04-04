package http.response;

<<<<<<< HEAD
import http.request.path.FilePath;
import http.request.message.RequestLine;

import java.io.FileInputStream;
=======
import http.request.HttpRequest;
import http.response.header.Header;
import http.response.header.HttpVersion;
import http.response.header.StatusCode;


import java.io.File;
>>>>>>> 2fb824f (refactor: response 생성 로직 캡슐화)
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static http.response.Path.RELATIVE_PATH;

public interface HttpResponse {
<<<<<<< HEAD
    public byte[] respond(FilePath httpRequest, RequestLine requestLine) throws IOException, URISyntaxException;

    static byte[] getRequestedFile(java.io.File file) throws IOException {
=======

    Response getResponse(File file, HttpRequest httpRequest) throws IOException;
>>>>>>> 2fb824f (refactor: response 생성 로직 캡슐화)

    default byte[] readFile(File file) throws IOException {
        StaticFileReader fileReader = new StaticFileReader();
        return fileReader.readFile(file);
    }
    default Response generateResponse(HttpRequest httpRequest, byte[] files, StatusCode code) throws IOException {
        String mimeType = httpRequest.getMimeType();
        int lengthOfBodyContent = files.length;
        Header header = new Header(HttpVersion.V_11, code, mimeType, lengthOfBodyContent);

        return new Response(header, files);
    }

    default Response generateRedirectResponse(HttpRequest httpRequest, byte[] files, String location) throws IOException {
        String mimeType = httpRequest.getMimeType();
        Header header = Header.redirectHeader(HttpVersion.V_11, mimeType, location);

        return new Response(header, files);
    }


    default void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    default void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

