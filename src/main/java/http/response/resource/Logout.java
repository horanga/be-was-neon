package http.response.resource;

public class Logout implements File{

    private final String mainPage = "index.hmtl";
    @Override
    public java.io.File getFile(String[] resourcePath) {
        return new java.io.File(RELATIVE_PATH, mainPage);
    }
}
