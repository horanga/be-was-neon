package http.request.path;

import java.io.File;

public class RegisterForm implements FilePath{
    private final String REGISTRATION_FOLDER = "registration/";
    @Override
    public File getFile(String[] resourcePath) {
        String path = joinUri(resourcePath);
        return new File(relativePath,REGISTRATION_FOLDER+path);
    }
}
