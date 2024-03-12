package http;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    public String[] getRequest(BufferedReader br) throws IOException {

        String requestHeader;
        String urlRequest;
        requestHeader = br.readLine();
        urlRequest = parseRequest(requestHeader);

        return new String[]{requestHeader, urlRequest};
    }


    public String parseRequest(String input) {
        String[] requestSplits = input.replaceAll("\\/", "").split(" ");
        return requestSplits[1].trim();
        //requestSplits[1]=요청 URL
    }
}
