package http.request.message;

public class RequestBody {

    private final String requestBody;
    private final String[] userInfo;

    public RequestBody(String requestBody, String[] userInfo) {
        this.requestBody = requestBody;
        this.userInfo = userInfo;
    }

    public String[] getUserInfo() {
        return userInfo;
    }
}
