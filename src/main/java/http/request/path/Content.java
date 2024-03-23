package http.request.path;

import java.io.File;

public class Content implements FilePath{
    @Override
    public File getFile(String[] resourcePath) {
        String path = joinUri(resourcePath);
        return new File(relativePath, path);
    }
}
