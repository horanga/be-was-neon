package webserver;

import db.Database;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestHandlerTest {

    @BeforeEach
    void clearDb() {
        Database.clear();
    }

    @DisplayName("index.html 리소스를 요청할 때 네트워크로 응답이 반환된다.")
    @Test
    void test3() throws IOException {
        Socket socketMock = mock(Socket.class);

        InputStream inputStreamMock = new ByteArrayInputStream("GET /index.html HTTP/1.1\n\n".getBytes());
        OutputStream outputStreamMock = new ByteArrayOutputStream();

        when(socketMock.getInputStream()).thenReturn(inputStreamMock);
        when(socketMock.getOutputStream()).thenReturn(outputStreamMock);

        RequestHandler requestHandler = new RequestHandler(socketMock, inputStreamMock, outputStreamMock);
        requestHandler.run();

        String output = outputStreamMock.toString();
        assertTrue(output.contains("HTTP/1.1 200 OK"));//응답확인
    }

    @DisplayName("회원가입 페이지를 요청할 때 요청할 때 네트워크로 응답이 반환된다.")
    @Test
    void test4() throws IOException {
        Socket socketMock = mock(Socket.class);

        InputStream inputStreamMock = new ByteArrayInputStream("GET /register.html HTTP/1.1\n\n".getBytes());
        OutputStream outputStreamMock = new ByteArrayOutputStream();

        when(socketMock.getInputStream()).thenReturn(inputStreamMock);
        when(socketMock.getOutputStream()).thenReturn(outputStreamMock);

        RequestHandler requestHandler = new RequestHandler(socketMock, inputStreamMock, outputStreamMock);
        requestHandler.run();

        String output = outputStreamMock.toString();
        assertTrue(output.contains("HTTP/1.1 200 OK"));//응답확인
    }

    @DisplayName("회원가입 폼을 작성하면")
    @Test
    void test5() throws IOException {
        URL url = new URL("http://localhost:8080/user/create?userId=javajigi&password=password&name=박재성&email=javajigi@slipp.net");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        assertThat(responseCode).isEqualTo(HttpURLConnection.HTTP_OK);

        User user = Database.findUserById("javajigi").orElse(null);
        assertThat(user.getName()).isEqualTo("박재성");
        assertThat(user.getEmail()).isEqualTo("javajigi@slipp.net");

    }

}