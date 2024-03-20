package http.request.factory;

import http.request.path.FilePath;

public interface PathFactory {

    FilePath getFilePath(String[] uri);

}
