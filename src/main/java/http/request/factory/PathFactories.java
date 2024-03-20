package http.request.factory;

import http.request.message.RequestLine;

import java.util.Arrays;
import java.util.List;

public enum PathFactories {
    MEMBERSHIP_PATH_FACTORY(new MembershipPathFactory(), Arrays.asList("create")),
    PAGE_PATH_FACTORY(new PagePathFactory(), Arrays.asList("index.html", "register.html")),
    CSS_PATH_FACTORY(new CssPathFactory(), Arrays.asList("reset.css", "main.css", "global.css")),
    ICO_PATH_FACTORY(new IcoPathFactory(), Arrays.asList("favicon.ico")),
    SVG_PATH_FACTORY(new SvgPathFactory(), Arrays.asList("img/bookMark.svg","img/ci_chevron-left.svg","img/ci_chevron-right.svg"
    ,"img/like.svg", "img/sendLink.svg","img/signiture.svg"));
    private final PathFactory pathFactory;
    private final List<String> pathList;

    PathFactories(PathFactory pathFactory, List<String> pathList) {
        this.pathFactory = pathFactory;
        this.pathList = pathList;
    }

    public static PathFactory choosePathFactory(RequestLine requestLine) {
        return Arrays.stream(PathFactories.values()).
                filter(factory -> requestLine.hasMatchingPath(factory.pathList)).
                map(factory -> factory.pathFactory).findFirst().get();
    }
}
