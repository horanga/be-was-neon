package http;

import java.io.*;

import static webserver.RequestHandler.logger;


public class HttpResponse {

    public void sendReponsed(String urlRequest, OutputStream out) throws IOException {
        byte[] reponse = getRequestedFile(urlRequest);
        DataOutputStream dos = new DataOutputStream(out);
        response200Header(dos, reponse.length);
        responseBody(dos, reponse);

    }

    private byte[] getRequestedFile(String urlRequest) throws IOException {
        final String relativePath = "src/main/resources/static/";
        File file = new File(relativePath, urlRequest);

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }

        return data;
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
