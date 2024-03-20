package http.request.factory;

import http.request.path.IcoFile;
import http.request.path.FilePath;

public class IcoPathFactory implements PathFactory {
    @Override
    public FilePath getFilePath(String[] uri) {
        return new IcoFile();
    }
}
