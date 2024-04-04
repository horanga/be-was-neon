package http.response.resource;

import static http.response.Path.REGISTRATION;
import static http.response.Path.RELATIVE_PATH;

public class RegisterForm implements File {
    @Override
    public java.io.File getFile(String[] resourcePath) {

        return new java.io.File(RELATIVE_PATH,REGISTRATION);
    }
}
