package db;

import http.ClientDatabase;
import http.UserDatabase;
import login.Cookie;
import login.LoginManager;
import login.SessionManager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class SessionDbTest {

    UserDatabase userDatabase = new UserDatabaseImpl();
    ClientDatabase clientDatabase = new ClientDatabaseImpl();
    SessionManager sessionManager = new SessionManager();
    @BeforeEach()
    void setting(){
        userDatabase.clear();
        clientDatabase.clear();
        sessionManager.clear();
    }
    @DisplayName("회원가입이 된 유저의 경우, 비밀번호를 잘못입력해서 로그인에 실패한다.")
    @Test
    void test1(){
        User user = new User("A", "B", "C", "D");
        userDatabase.addUser(user);

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.login("A", "B");

        assertThat(login).isFalse();
    }

    @DisplayName("등록되지 않은 회원 정보로 로그인하면 실패한다.")
    @Test
    void test2(){

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.login("A", "C");
        assertThat(login).isFalse();
    }

    @DisplayName("등록된 유저의 정보로 로그인하면 성공한다.")
    @Test
    void test3(){
        User user = new User("A", "B", "C", "D");

        userDatabase.addUser(user);

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.login("A", "C");
        Map<String, User> userList = userDatabase.getUser();
        Optional<User> userById = userDatabase.findUserById("A");

        assertSoftly(sofly->{
           sofly.assertThat(login).isTrue();
           sofly.assertThat(userList.size()).isEqualTo(1);
           sofly.assertThat(user).isEqualTo(userById.get());
        });

    }
    @DisplayName("로그아웃 하면 세션과 쿠키 정보가 사라진다.")
    @Test
    void test4(){
        User user = new User("A", "B", "C", "D");
        userDatabase.addUser(user);

        LoginManager loginManager = new LoginManager();
        loginManager.login("A", "C");

        Map<String, Cookie> cookieDatabase = clientDatabase.getCookieDatabase();
        Map<String, User> loginSessionMap = sessionManager.getLoginSessionMap();


        assertSoftly(sofly->{
            sofly.assertThat(cookieDatabase.size()).isEqualTo(1);
            sofly.assertThat(loginSessionMap.size()).isEqualTo(1);

        });

        LoginManager loginManager1 = new LoginManager();
        loginManager1.logout();

        assertSoftly(sofly->{
            sofly.assertThat(cookieDatabase.size()).isEqualTo(0);
            sofly.assertThat(loginSessionMap.size()).isEqualTo(0);

        });


    }

}
