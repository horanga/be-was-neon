package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;

    private static final int COUNT_OF_THREAD = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_THREAD);

    public static void main(String args[]) throws Exception {
        int port = determinePort(args);
        connectSocket(port);

    }

    private static int determinePort(String[] args) {
        if (args != null && args.length > 0) {
            return Integer.parseInt(args[0]);

            //적어도 포트를 한줄 이상 입력하면 그 숫자에 맞춰서 소켓 생성
            //커맨드라인 입력창이 String으로 입력을 받아서 숫자로 변환함
        }
        return DEFAULT_PORT;
        //입력받은 값이 없으면 내 컴퓨터의 포트번호인 8080로 접속한다.
    }

    private static void connectSocket(int port) throws IOException {

        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                startThread(connection);
            }
        }
    }

    private static void startThread(Socket connection) throws IOException {
        executorService.submit(new RequestHandler(connection, connection.getInputStream(), connection.getOutputStream()));
    }
}
