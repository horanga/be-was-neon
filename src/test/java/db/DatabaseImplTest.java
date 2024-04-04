package db;

import login.LoginManager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DatabaseImplTest {
//
//    @BeforeEach
//    void clearDb() {
//        DatabaseImpl.clear();
//    }
//    //현재 Db가 static이라서 db를 전역변수로 사용하고 있음.
//    // Db를 정리해주지 않으면 테스트 간 격리가 되지 않아서 서로 결과에 영향을 준다.
//
//    @DisplayName("데이터 베이스에 유저 데이터를 넣고, 유저 아이디로 찾은 것과 유저 데이터가 동일한지 확인한다")
//    @Test
//    void test1() {
//
//        User jeong = new User("jeong", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(jeong);
//        User cheol = new User("cheol", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(cheol);
//
//        User userA = DatabaseImpl.findUserById("jeong").orElseThrow(() -> new IllegalArgumentException());
//        User userB = DatabaseImpl.findUserById("cheol").orElseThrow(() -> new IllegalArgumentException());
//
//
//        assertThat(jeong).isEqualTo(userA);
//        assertThat(cheol).isEqualTo(userB);
//    }
//
//    @DisplayName("데이터 베이스에 없는 유저의 아이디로 유저를 찾으면 null값을 반환한다.")
//    @Test
//    void test2() {
//
//        User jeong = new User("jeong", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(jeong);
//        User cheol = new User("cheol", "123", "jeong", "ad@naver.com");
//
//        assertThatThrownBy(() -> DatabaseImpl.findUserById("cheol").orElseThrow(() -> new IllegalArgumentException()))
//                .isInstanceOf(IllegalArgumentException.class);
//
//    }
//
//    @DisplayName("데이터 베이스에 저장한 유저를 모두 찾아서, 저장 데이터와 동일한지 확인한다.")
//    @Test
//    void test3() {
//
//
//        User jeong = new User("jeong", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(jeong);
//        User cheol = new User("cheol", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(cheol);
//
//
//        Collection<User> all = DatabaseImpl.findAll();
//        assertThat(all.stream().anyMatch(i -> i.equals(jeong) || i.equals(cheol))).isTrue();
//    }
//
//    @DisplayName("데이터베이스에 유저를 저장하지 않은 채 모든 유저를 찾으면 데이터가 반환되지 않는다.")
//    @Test
//    void test4() {
//
//        Collection<User> all = DatabaseImpl.findAll();
//        assertThat(all).isEmpty();
//    }
//
//    @DisplayName("유저의 id와 비밀번호가 정확히 저장되는지 확인한다.")
//    @Test
//    void test5() {
//
//
//        User jeong = new User("jeong", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(jeong);
//        User cheol = new User("cheol", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(cheol);
//
//        LoginManager checker = new LoginManager();
//        boolean isAccountCreated = checker.isAccountInfoValid("jeong", "123");
//        assertThat(isAccountCreated).isTrue();
//    }
//
//    @DisplayName("db에 저장되지 않은 id와 비밀번호로 로그인을 시도하면 실패한다.")
//    @Test
//    void test6() {
//
//
//        User jeong = new User("jeong", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(jeong);
//        User cheol = new User("cheol", "123", "jeong", "ad@naver.com");
//        DatabaseImpl.addUser(cheol);
//
//        LoginManager checker = new LoginManager();
//        boolean isAccountCreated = checker.isAccountInfoValid("kim", "123");
//        assertThat(isAccountCreated).isFalse();
//    }

}