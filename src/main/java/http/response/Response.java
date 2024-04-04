package http.response;

import http.response.header.Header;

public class Response {

    private final Header header;
    private final byte[] file;

    public Response(Header header, byte[] file) {
        this.header = header;
        this.file = file;
    }

    public Header getHeader() {
        return header;
    }

    public byte[] getFileAsByte() {
        return file;
    }

    public String getHeaderMessage(){
       return header.getMessage();
    }

}
