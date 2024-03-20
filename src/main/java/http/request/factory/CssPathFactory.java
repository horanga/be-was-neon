package http.request.factory;

import http.request.path.css.GlobalCss;
import http.request.path.css.MainCss;
import http.request.path.FilePath;
import http.request.path.css.ResetCss;

import java.util.Arrays;

public class CssPathFactory implements PathFactory {
    @Override
    public FilePath getFilePath(String[] uri) {
        return CssType.chooseCssType(uri);
    }


    public enum CssType{
       MAIN("/main.css", new MainCss()),
       RESET("/reset.css", new ResetCss()),
       GLOBAL("/global.css", new GlobalCss());


        String path;
        FilePath pathInstance;

        CssType(String path, FilePath pathInstance) {
            this.path = path;
            this.pathInstance = pathInstance;
        }


        public static FilePath chooseCssType(String[] requestLine) {
            return Arrays.stream(CssPathFactory.CssType.values()).filter(i ->
                    Arrays.asList(requestLine).contains(i.path)).findFirst().
                    map(cssType -> cssType.pathInstance).get();
        }


    }
}
