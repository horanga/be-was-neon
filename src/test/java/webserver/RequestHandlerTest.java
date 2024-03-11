package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestHandlerTest {


    @DisplayName("HTTP 요청 처리가 정상 작동하는지 검증한다")
    @Test
    void test3() throws IOException {
        Socket socketMock = mock(Socket.class);
        InputStream inputStreamMock = new ByteArrayInputStream("GET /index.html HTTP/1.1\n\n".getBytes());
        OutputStream outputStreamMock = new ByteArrayOutputStream();

        when(socketMock.getInputStream()).thenReturn(inputStreamMock);
        when(socketMock.getOutputStream()).thenReturn(outputStreamMock);

        RequestHandler requestHandler = new RequestHandler(socketMock);
        requestHandler.run();
    }

}