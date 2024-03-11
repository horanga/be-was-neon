package utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlParserTest {
    @DisplayName("requestHeader에 있는 요청 URL path를 추출한다.")
    @Test
    void test1(){
      String HeaderLine = "GET /index.html HTTP/1.1";
        String urlRequest = UrlParser.parseRequest(HeaderLine);

        assertThat(urlRequest).isEqualTo("index.html");
    }
}