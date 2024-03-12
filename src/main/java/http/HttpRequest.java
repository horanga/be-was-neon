package http;

import java.io.BufferedReader;
import java.io.IOException;

public interface HttpRequest {

    public String getRequest() throws IOException;

}
