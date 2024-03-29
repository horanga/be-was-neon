package db;

import http.Database;
import login.LoginManager;
import model.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class SessionDbTest {

    Database database = new DatabaseImpl();
    @BeforeEach()
    void setting(){
        database.clear();
    }
    @DisplayName("회원가입이 된 유저의 경우, 비밀번호를 잘못입력해서 로그인에 실패한다.")
    @Test
    void test1(){
        User user = new User("A", "B", "C", "D");
        database.addUser(user);

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

        database.addUser(user);

        LoginManager loginManager = new LoginManager();
        boolean login = loginManager.login("A", "C");
        Map<String, User> userList = database.getUser();
        Optional<User> userById = database.findUserById("A");

        assertSoftly(sofly->{
           sofly.assertThat(login).isTrue();
           sofly.assertThat(userList.size()).isEqualTo(1);
           sofly.assertThat(user).isEqualTo(userById.get());
        });

    }

}
