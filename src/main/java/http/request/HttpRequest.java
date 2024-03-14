package http.request;

import java.io.IOException;

public interface HttpRequest {

    public String getResourcePath() throws IOException;
}

