package http;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestFactory {

    public static HttpRequest getHttpRequest(String str) {
        String request = parse(str);

        if (request.equals("index.html")) {
            return new PostRequest();
        }

        return new JoinRequest();
    }

    private static String parse(String input) {
        String[] requestSplits = input.replaceAll("\\/", "").split(" ");
        return requestSplits[1].trim();
        //requestSplits[1]=요청 URL
    }

    public static String getRequestHeader(BufferedReader br) throws IOException {
        return br.readLine();
    }
}
