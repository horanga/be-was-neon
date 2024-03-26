package http.request.message.parser;


import http.request.message.RequestMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Parser {

    RequestMessage parse(String requestLine, BufferedReader buffer) throws IOException;

    default String getMethod(String requestLine){
        return requestLine.split(" ")[0].trim();
    }

    default int getSubsequentLines(BufferedReader buffer, List<String> subsequentLines) throws IOException {
        String line;
        int MessageSize = 0;
        while ((line = buffer.readLine()) != null && !line.isEmpty()) {
            if (line.contains("Content-Length")) {//size

                String[] split = line.split(":");
                MessageSize = Integer.parseInt(split[1].trim());
            }

            subsequentLines.add(line);
        }
        return MessageSize;
    }
}
