package http.request.path.css;

import http.request.path.FilePath;

public class ResetCss implements FilePath {

    @Override
    public String getResourcePath() {
        return "reset.css";
    }
}
