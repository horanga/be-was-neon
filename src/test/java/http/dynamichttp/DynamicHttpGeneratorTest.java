package http.dynamichttp;


import db.ClientDatabaseImpl;
import http.ClientDatabase;
import model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicHttpGeneratorTest {

    @BeforeEach
    void setting(){
        ClientDatabase database = new ClientDatabaseImpl();
        database.clear();
    }

    @DisplayName("이용자 정보 중 email 정보가 없으면 실패한다.")
    @Test
    void test1(){

        User user = User.loginInfo("dugsh", "연호");
        List<User> list = new ArrayList<>();
        list.add(user);


        DynamicHttpGenerator dynamicHttpGenerator = new DynamicHttpGenerator();
        assertThatThrownBy
                (()->dynamicHttpGenerator.joinUserInfoList(list)).isInstanceOf(NullPointerException.class);

    }

    @DisplayName("이용자 리스트가 비어있으면 실패한다.")
    @Test
    void test2(){

        List<User> list = new ArrayList<>();

        DynamicHttpGenerator dynamicHttpGenerator = new DynamicHttpGenerator();
        assertThatThrownBy
                (()->dynamicHttpGenerator.joinUserInfoList(list)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이용자 리스트로 null값이 오면 실패한다.")
    @Test
    void test3(){

        List<User> list = null;

        DynamicHttpGenerator dynamicHttpGenerator = new DynamicHttpGenerator();
        assertThatThrownBy
                (()->dynamicHttpGenerator.joinUserInfoList(list)).
                isInstanceOf(NullPointerException.class);
    }

    @DisplayName("이용자 두명의 아이디, 이름, email 정보를 나타내는 html 코드를 만든다.")
    @Test
    void test4(){

        User user1 = new User("dusgh", "정연호", "123", "dusgh@Naver.com");
        User user2 = new User("cheol", "정철호", "123", "choel@Naver.com");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        DynamicHttpGenerator dynamicHttpGenerator = new DynamicHttpGenerator();
        String modifiedHtml = dynamicHttpGenerator.joinUserInfoList(list);

        System.out.println(modifiedHtml);

        Document doc = Jsoup.parse(modifiedHtml);

        Element tr = doc.select("tr").get(1);
        assertTrue(tr.text().contains("dusgh"));
        assertTrue(tr.text().contains("정연호"));
        assertTrue(tr.text().contains("dusgh@Naver.com"));

        Element tableRow2 = doc.select("tr").get(3);
        assertTrue(tableRow2.text().contains("cheol"));
        assertTrue(tableRow2.text().contains("정철호"));
        assertTrue(tableRow2.text().contains("choel@Naver.com"));



    }

}