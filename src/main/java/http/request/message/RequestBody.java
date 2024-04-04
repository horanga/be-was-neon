package http.request.message;

import java.util.Arrays;

public class RequestBody {
    private final String requestBody;
    private final String[] userInfo;

    public RequestBody(String requestBody, String[] userInfo) {
        this.requestBody = requestBody;
        this.userInfo = userInfo;
    }
    public String[] getUserInfo() {
        return Arrays.copyOf(userInfo, userInfo.length);
    }
}
