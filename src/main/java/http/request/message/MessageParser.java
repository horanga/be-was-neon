package http.request.message;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MessageParser {

    private static final Pattern pattern = Pattern.compile("GET\s+(.*?)\s+HTTP");

    public RequestMessage parseRequestMessage(BufferedReader buffer) throws IOException {

        String requestLine = buffer.readLine();
        String mimeType = ContentType.getMimeType(requestLine);
        List<String> subsequentLines = new ArrayList<>();
        getSubsequentLines(buffer, subsequentLines);

        return new RequestMessage(new RequestLine(requestLine, ParserType.parse(requestLine), mimeType)
                , subsequentLines);
    }

    private static void getSubsequentLines(BufferedReader buffer, List<String> subsequentLines) throws IOException {
        String line;
        while ((line = buffer.readLine()) != null && !line.isEmpty()) {
            subsequentLines.add(line);
        }
    }

    private static String[] parseMembershipRequest(String request) {
        String[] split = request.substring(request.indexOf("?") + 1).split("&");
        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).toArray(String[]::new);
    }

    private static String[] parseFileRequest(String input) {
        Matcher matcher = pattern.matcher(input);
        String path = "";
        if (matcher.find()) {
            path = matcher.group(1);
        }
        return Stream.of(path.split(" ")).filter(i -> !i.isEmpty())
                .toArray(String[]::new);
    }

    public enum ParserType {
        PARSER_FOR_FILE_REQUEST(Arrays.asList("html", "css", "ico", "svg", "png"), MessageParser::parseFileRequest),
        PARSER_FOR_MEMBERSHIP_REQUEST(Arrays.asList("/register?"), MessageParser::parseMembershipRequest);

        private final List<String> matchingKeyword;
        private final Function<String, String[]> parseStrategy;

        ParserType(List<String> matchingKeyword, Function<String, String[]> parseStrategy) {
            this.matchingKeyword = matchingKeyword;
            this.parseStrategy = parseStrategy;
        }

        public static String[] parse(String input) {
            Optional<ParserType> parserType = Stream.of(ParserType.values()).
                    filter(i -> i.hasMatchingKeyword(input)).
                    findFirst();

            return parserType.map(j -> j.parseStrategy.apply(input)).orElse(null);
        }

        private boolean hasMatchingKeyword(String input) {
            return matchingKeyword.stream().anyMatch(input::contains);
        }
    }
}
