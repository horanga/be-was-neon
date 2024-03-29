package http.response.resource;

public class Login implements File {
    @Override
    public java.io.File getFile(String[] resourcePath) {
        String uri = joinPath(resourcePath);
        String path = uri.replace("user/", "login/");
        //static/login 폴더 구조로 돼 있어서 user로 들어오는 경로값을 login으로 변경

        return new java.io.File(RELATIVE_PATH, path);
    }
}
