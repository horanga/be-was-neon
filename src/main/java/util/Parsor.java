package util;

import model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parsor {

    public static String parse(String input) {
        String[] requestSplits = input.replaceAll("\\/", "").split(" ");
        return requestSplits[1].trim();
        //requestSplits[1]=요청 URL
    }

    public static List<String> pasrseJoinRequest(String request) {
        String[] split = request.substring(request.indexOf("?") + 1).split("&");
        return Arrays.stream(split).map(i->i.substring(i.indexOf("=")+1)).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        String a = "register?id=1&username=2&password=3&email=4";
        List<String> strings = pasrseJoinRequest(a);
        System.out.println(strings);
    }
}
