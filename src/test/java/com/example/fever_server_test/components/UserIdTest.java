package com.example.fever_server_test.components;

import com.example.fever_server_test.social.SocialLoginType;
import com.example.fever_server_test.social.SocialOauth;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserIdTest {
    @Test
    public void receiveAllType() {
        String socialLoginType = "KAKAO";
        List<String> list = Stream
                .of(SocialLoginType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        String idType = "PHONE";
        for (String e : list) {
            if (e.equals(socialLoginType))
                idType = e;
        }
        UserId userId = new UserId(idType, 2111909239);
        assertThat(userId.getColName()).isEqualTo("user_kakao_id");
    }

    @Test
    public void getIdValueTest() {
        // given
        String body = "{\"id\":2111909239}";
        JSONObject jsonObject = new JSONObject(body);

        // when
        UserId userId = new UserId("KAKAO", jsonObject.get("id"));
        String when = userId.getValue();

        // then
        assertThat(when).isEqualTo("2111909239");
    }
}
