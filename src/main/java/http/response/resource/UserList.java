package http.response.resource;

import static http.response.Path.RELATIVE_PATH;
import static http.response.Path.USERLIST;

public class UserList implements File{
    @Override
    public java.io.File getFile(String[] resourcePath) {
        return new java.io.File(RELATIVE_PATH,USERLIST);
    }
}
