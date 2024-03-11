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

    public static void main(String args[]) throws Exception {
        int port = determinePort(args);
        generateSocket(port);
    }

    private static int determinePort(String[] args) {
        if (args != null && args.length > 0) {
            return Integer.parseInt(args[0]);
            //port
        }
        //웹서버는 기본적으로 8080번 포트를 사용한다.
        return DEFAULT_PORT;
    }

    private static void generateSocket(int port) throws IOException {
        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                startThread(connection);
            }
        }
    }

    private static void startThread(Socket connection) {
        //Runtime.getRuntime().availableProcessors()을 통해서 cpu 스레드가 4개로 나와서 이에 맞춰 스레드 개수 설정함
        //아직까지 비동기나 동기화와 관련된 요구사항이 없어서 단순한 스레드풀을 생성함
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new RequestHandler(connection));
    }
}
