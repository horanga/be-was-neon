package http.factory;

import http.request.HttpRequest;
import http.request.Membership;
import http.response.HttpResponse;
import http.response.JoinResponse;
import http.response.PageResponse;

public class ResponseFactory {

    public static HttpResponse getResponse(HttpRequest httpRequest){
        if(httpRequest instanceof Membership){
            return new JoinResponse();
        } else{
            return new PageResponse();
        }
    }

}
