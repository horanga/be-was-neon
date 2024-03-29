package http.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class StaticFileReader {

    public byte[] readFile(File file) throws IOException {

        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(data);
        }
        return data;
    }
}
