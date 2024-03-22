package http.request.path.css;

import http.request.path.FilePath;

public class GlobalCss implements FilePath {
    @Override
    public String getResourcePath() {
        return "global.css";
    }
}
