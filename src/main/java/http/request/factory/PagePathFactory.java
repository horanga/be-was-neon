package http.request.factory;

import http.request.path.FilePath;
import http.request.path.html.JoinPage;
import http.request.path.html.PostPage;

import java.util.Arrays;

public class PagePathFactory implements PathFactory {

    @Override
    public FilePath getFilePath(String[] uri) {

        return PageType.choosePageType(uri);
    }

    enum PageType {
        POSTPAGE("/index.html", new PostPage()),
        JOINPAGE("/register.html", new JoinPage());

        String path;
        FilePath pathInstance;

        PageType(String path, FilePath pathInstance) {
            this.path = path;
            this.pathInstance = pathInstance;
        }

        public static FilePath choosePageType(String[] requestLine) {
            return Arrays.stream(PageType.values()).filter(i ->
                    Arrays.asList(requestLine).contains(i.path)).findFirst().
                    map(pageType -> pageType.pathInstance).get();
        }
    }
}

