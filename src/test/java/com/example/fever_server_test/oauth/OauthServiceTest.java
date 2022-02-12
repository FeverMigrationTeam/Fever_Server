package com.example.fever_server_test.oauth;

import com.example.fever_server_test.service.OauthService;
import com.example.fever_server_test.social.SocialLoginType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OauthServiceTest {

    @Autowired
    private OauthService oauthService;

    @Test
    public void requestUserTest() {
        // given
        String token = "fRR9_CbMqAbssq2g7o6zA5Rj6eh1bt1HJZl0NQopyNgAAAF-641dwQ";

        // when
        String when = oauthService.requestSocialData(SocialLoginType.KAKAO, token);

        // then
        assertThat(when).isEqualTo("FAIL");  // fail시 메시지 확인. body가 잘 받아져 왔으면 test complete
    }

    @Test
    public void getIdTest() {
        // given
        String body = "{\"id\":2111909239}";
        JSONObject jsonObject = new JSONObject(body);

        // when
        String when = jsonObject.getBigInteger("id").toString();

        // then
        assertThat(when).isEqualTo("2111909239");
    }

}
