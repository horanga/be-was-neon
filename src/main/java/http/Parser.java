package http;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Parser {
    public static final Pattern pattern = Pattern.compile("GET\s+(.*?)\s+HTTP");
    public String[] parse(String input) {
        return ParserType.parse(input);
    }

    private static String[] parseJoinRequest(String request) {
        String[] split = request.substring(request.indexOf("?") + 1).split("&");
        return Arrays.stream(split).map(i -> i.substring(i.indexOf("=") + 1)).toArray(String[]::new);
    }

    private static String[] parsePageRequest(String input) {
        Matcher matcher = pattern.matcher(input);
        String path = "";
        if (matcher.find()) {
            path = matcher.group(1);
        }
        return Stream.of(path.split("/")).filter(i -> !i.isEmpty())
                .toArray(String[]::new);
    }
    public enum ParserType {
        PARSER_FOR_PAGE_REQUEST(Arrays.asList("/index.html", "/register.html"), Parser::parsePageRequest),
        PARSER_FOR_MEMBERSHIP_REQUEST(Arrays.asList("/register?"), Parser::parseJoinRequest);

        List<String> keywordForParsing;
        Function<String, String[]> parseType;

        ParserType(List<String> keywordForParsing, Function<String, String[]> parseType) {
            this.keywordForParsing = keywordForParsing;
            this.parseType = parseType;
        }

        public static String[] parse(String input) {
            Optional<ParserType> parserType = Stream.of(ParserType.values()).
                    filter(i -> i.isKeyWordContained(input)).
                    findFirst();

            return parserType.map(j -> j.parseType.apply(input)).orElse(null);
        }

        public boolean isKeyWordContained(String input) {
            return keywordForParsing.stream().anyMatch(i -> input.contains(i));
        }
    }


}
