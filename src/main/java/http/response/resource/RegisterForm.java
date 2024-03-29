package http.response.resource;

public class RegisterForm implements File {
    private final String REGISTRATION_FOLDER = "registration/";
    @Override
    public java.io.File getFile(String[] resourcePath) {
        String path = joinPath(resourcePath);
        return new java.io.File(RELATIVE_PATH,REGISTRATION_FOLDER+path);
    }
}
