package http.request.message.parser;


import http.request.message.RequestMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface Parser {

    RequestMessage parse(String requestLine, BufferedReader buffer) throws IOException;

    default int getSubsequentLines(BufferedReader buffer, List<String> subsequentLines) throws IOException {
        String line;
        int MessageSize = 0;
        while ((line = buffer.readLine()) != null && !line.isEmpty()) {
            if (line.contains("Content-Length")) {//size

                // requestHeaeder

                //공백 버퍼가 여기서 끝나버리는거예요

                //reqeustBody

                String[] split = line.split(":");
                MessageSize = Integer.parseInt(split[1].trim());
            }

            subsequentLines.add(line);
        }
        return MessageSize;
    }


    public enum ParserType {
        PARSER_FOR_FILE_REQUEST(Arrays.asList("html", "css", "ico", "svg", "png")),
        PARSER_FOR_MEMBERSHIP_REQUEST(Arrays.asList("/register?"));

        private final List<String> matchingKeyword;

        ParserType(List<String> matchingKeyword) {
            this.matchingKeyword = matchingKeyword;

        }

//        public static String[] parse(String input) {
//            Optional<Parser.ParserType> parserType = Stream.of(Parser.ParserType.values()).
//                    filter(i -> i.hasMatchingKeyword(input)).
//                    findFirst();
//
//            return parserType.map(j -> j.parseStrategy.apply(input)).orElse(null);
//        }

        private boolean hasMatchingKeyword(String input) {
            return matchingKeyword.stream().anyMatch(input::contains);
        }
    }
}
