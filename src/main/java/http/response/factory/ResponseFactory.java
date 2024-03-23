package http.response.factory;

import http.request.path.*;
import http.request.path.css.GlobalCss;
import http.request.path.css.ResetCss;
import http.request.path.html.JoinPage;
import http.request.path.html.Membership;
import http.request.path.html.PostPage;
import http.request.path.svg.*;
import http.response.HttpResponse;
import http.response.MembershipResponse;
import http.response.PageResponse;

import java.util.Arrays;
import java.util.List;

public enum ResponseFactory {

    MEMBERSHIP_RESPONSE(new MembershipResponse(), Arrays.asList(new Membership())),
    PAGE_RESPONSE(new PageResponse(), Arrays.asList(new JoinPage(), new PostPage(),
            new GlobalCss(), new ResetCss(), new IcoFile(), new BookMark(), new ChevronLeft(), new ChevronRight(),
            new Like(), new SendLink(), new SignitureSvg()));

    HttpResponse httpResponse;
    List<FilePath> requestsList;

    ResponseFactory(HttpResponse httpResponse, List<FilePath> requestsList) {
        this.httpResponse = httpResponse;
        this.requestsList = requestsList;
    }

    public static HttpResponse chooseResponse(FilePath httpRequest) {
        return Arrays.stream(ResponseFactory.values())
                .filter(resonponse -> resonponse.matches(httpRequest))
                .map(i -> i.httpResponse).findFirst().get();
    }

    public boolean matches(FilePath httpRequest) {
        return requestsList.stream()
                .anyMatch(i-> i.getClass().equals(httpRequest.getClass()));
    }
}
