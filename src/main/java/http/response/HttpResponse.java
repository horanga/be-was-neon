package http.response;

import http.request.path.FilePath;
import http.request.message.RequestLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public interface HttpResponse {
    public byte[] respond(FilePath httpRequest, RequestLine requestLine) throws IOException, URISyntaxException;

    static byte[] getRequestedFile(java.io.File file) throws IOException {

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }

}
