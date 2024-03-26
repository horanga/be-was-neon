package http.request.path;

import java.io.File;

public class MemberShip implements FilePath{
    private final String MAIN_PAGE = "index.html";
    @Override
    public File getFile(String[] resourcePath) {
        String path = joinUri(resourcePath);
        return new File(relativePath, MAIN_PAGE);
    }
}
