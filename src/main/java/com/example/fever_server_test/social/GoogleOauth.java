package com.example.fever_server_test.social;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {

//    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
//    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
//    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    private String GOOGLE_SNS_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private String GOOGLE_SNS_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "profile");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

//    Spring Boot RestTemplate 활용
    @Override
    public String requestAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);

        if( responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        return "구글 로그인 요청 처리 실패";
    }

    @Override
    public String requestSocialData(String token) {
        return "";
    }
}
