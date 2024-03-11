package util;

import java.io.BufferedReader;
import java.io.IOException;

public class HeaderPrinter {

    public static void printRequestHeader(String httpRequestHeader, BufferedReader br) throws IOException {
        System.out.println(httpRequestHeader);

        while (!httpRequestHeader.isEmpty()) {
            httpRequestHeader = br.readLine();
            System.out.println(httpRequestHeader);
        }
    }
}
