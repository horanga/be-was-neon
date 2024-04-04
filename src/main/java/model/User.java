package model;

import java.util.logging.Logger;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(){};

    public User(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static User loginInfo(String userId, String password){
        User user = new User();
        user.userId=userId;
        user.password =password;

        return user;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }

}
