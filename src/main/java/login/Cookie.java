package login;

public class Cookie {
    private final String sid;
    private final String path = "/";

    public Cookie(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }
}
