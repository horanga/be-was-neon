package http.request.factory;

import http.request.path.FilePath;
import http.request.path.IcoFile;
import http.request.path.css.GlobalCss;
import http.request.path.css.ResetCss;
import http.request.path.html.JoinPage;
import http.request.path.html.Membership;
import http.request.path.html.PostPage;
import http.request.path.svg.*;

import java.util.Arrays;

public enum PathFactories {
    POSTPAGE("/index.html", new PostPage()),
    JOINPAGE("/register.html", new JoinPage()),

    RESET("/reset.css", new ResetCss()),
    GLOBAL("/global.css", new GlobalCss()),
    BOOKMARK("/img/bookMark.svg", new BookMark()),
    CHEVRON_LEFT("/img/ci_chevron-left.svg", new ChevronLeft()),
    CHEVRON_RIGHT("/img/ci_chevron-right.svg", new ChevronRight()),
    LIKE("/img/like.svg", new Like()),
    SENDLINK("/img/sendLink.svg", new SendLink()),
    SIGNITURE("/img/signiture.svg", new SignitureSvg()),
    ICO("/favicon.ico", new IcoFile()),
    MEMBERSHIP("/create", new Membership());

    String path;
    FilePath pathInstance;

    PathFactories(String path, FilePath pathInstance) {
        this.path = path;
        this.pathInstance = pathInstance;
    }

    public static FilePath getPath(String[] requestLine) {
        return Arrays.stream(PathFactories.values()).filter(i ->
                        Arrays.asList(requestLine).contains(i.path)).findFirst().
                map(pageType -> pageType.pathInstance).get();

    }
}
