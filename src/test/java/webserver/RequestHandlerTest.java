package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebServerTest {


    @DisplayName("소켓이 정상 연결된다")
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