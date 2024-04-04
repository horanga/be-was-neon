package http.response;

import http.response.Responsesender.NormalResponseSender;
import http.response.header.Header;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static http.response.Path.NOT_FOUND_ERROR;
import static http.response.Path.RELATIVE_PATH;

public class ErrorManager {
    public void respondToError(OutputStream out) throws IOException {
        File file = new File(RELATIVE_PATH,NOT_FOUND_ERROR);
        ResponseSender sender = new NormalResponseSender();
        StaticFileReader fileReader = new StaticFileReader();
        byte[] fileToBytes = fileReader.readFile(file);
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        Header header = new Header();

        sender.sendHeader(dataOutputStream, header.getErrorMessage());
        sender.sendBody(dataOutputStream, fileToBytes);
    }
}
