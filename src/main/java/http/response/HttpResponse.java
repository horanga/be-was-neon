package http.response;

import http.request.message.RequestMessage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public interface HttpResponse {
    public Response respond(File file, RequestMessage requestMessage) throws IOException, URISyntaxException;

    default byte[] getRequestedFile(java.io.File file) throws IOException {

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }
}
