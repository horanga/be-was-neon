package http.dynamichttp;

import model.User;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


public class DynamicHttpGenerator {

    private final String USERNAME_PLACEHOLDER = "username";
    private final String USERLIST_PLACEHOLDER = "이용자 정보";

    public byte[] generateHtmlForUserId(byte[] file, User user) {
        return replacePlaceholderWithContent(file, USERNAME_PLACEHOLDER, user.getUserId());
    }

    public byte[] generateHtmlForUserList(byte[] file, Collection<User> userList) {
        return replacePlaceholderWithContent(file, USERLIST_PLACEHOLDER, joinUserInfoList(userList));
    }

    public String joinUserInfoList(Collection<User> userList) {

        Objects.requireNonNull(userList, "User list must not be null");

        if (userList.isEmpty()){
            throw new IllegalArgumentException("User list must not be empty.");
        }

        return userList.stream()
                .map(user ->
                        String.format("<table><tr><th>User ID</th><th>Name</th><th>Email</th></tr><tr><td>%s</td><td>%s</td><td>%s</td></tr></table>",
                        Objects.requireNonNull(user.getUserId(), "User ID must not be null"),
                        Objects.requireNonNull(user.getName(), "Name must not be null"),
                        Objects.requireNonNull(user.getEmail(), "Email must not be null")))
                .collect(Collectors.joining());
    }

    private byte[] replacePlaceholderWithContent(byte[] file, String placeHolder, String content) {
        String html = new String(file, StandardCharsets.UTF_8);
        String modifiedHtml = html.replace(placeHolder, content);
        return modifiedHtml.getBytes(StandardCharsets.UTF_8);
    }
}