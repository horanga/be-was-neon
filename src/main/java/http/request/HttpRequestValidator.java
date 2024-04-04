package http.request;

import exception.InvalidHttpMethodException;
import exception.InvalidPostRequestException;
import http.request.message.RequestHeader;
import http.request.message.RequestLine;

import java.util.Objects;

public class HttpRequestValidator {

    public void validate(HttpRequest httpRequest){
        validateRequestLine(httpRequest);
        validateHeader(httpRequest);
        validateBody(httpRequest);
    }

    private void validateRequestLine(HttpRequest httpRequest) {
        RequestLine requestLine = httpRequest.getRequestLine();

        Objects.requireNonNull(requestLine.getRequestLine());
        Objects.requireNonNull(requestLine.getUri());
        Objects.requireNonNull(requestLine.getMethod());

        if(!requestLine.isMethodValid()){
            throw new InvalidHttpMethodException("유효하지 않은 메서드입니다");
        }
    }


    private void validateHeader(HttpRequest httpRequest) {
        RequestHeader requestHeader = httpRequest.getRequestHeader();
        Objects.requireNonNull(requestHeader.getRequestHeader());
        Objects.requireNonNull(requestHeader.getMimeType());
    }


    private void validateBody(HttpRequest httpRequest) {
        if(httpRequest.isPostRequest()&&!httpRequest.hasRequestBody()){
            throw new InvalidPostRequestException("httprequest에 reqeustbody가 없습니다.");
        }

    }



}
