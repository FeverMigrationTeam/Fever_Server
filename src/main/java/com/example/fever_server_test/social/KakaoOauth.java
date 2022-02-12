package com.example.fever_server_test.social;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOauth implements SocialOauth {

    //    @Value("${sns.kakao.client.id}")
    private String KAKAO_SNS_CLIENT_ID = "bfde5086fc94e9b3c624fa64c5fb1afb";
    //    @Value("${sns.kakao.client.secret}")
//    private String KAKAO_SNS_CLIENT_SECRET;
//    @Value("${sns.kakao.callback.url}")
    private String KAKAO_SNS_CALLBACK_URL = "http://localhost:8080/oauth/KAKAO/callback";
    private String KAKAO_SNS_BASE_URL = "https://kauth.kakao.com/oauth/authorize";
    private String KAKAO_SNS_TOKEN_BASE_URL = "https://kauth.kakao.com/oauth/token";

    private final String KAKAO_SNS_USER_API_URL = "https://kapi.kakao.com/v2/user/me";

    @Override
    public String getOauthRedirectURL() {

        Map<String, Object> params = new HashMap<>();
        params.put("client_id", KAKAO_SNS_CLIENT_ID);
        params.put("redirect_uri", KAKAO_SNS_CALLBACK_URL);
        params.put("response_type", "code");

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return KAKAO_SNS_BASE_URL + "?" + parameterString;
    }

    //    Spring Boot RestTemplate 활용
    @Override
    public String requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate(); // Spring HTTP 통신 템플릿, HTTP 요청 후 JSON, String .. 과 같은 응답을 받을 수 있는 템플릿 -> 주로 외부 api를 호출할 때 사용함.

        // --- Body ---
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // Query string을 사용하는 경우 multivalueMap 사용
        params.add("grant_type", "authorization_code"); // 고정 값
        params.add("client_id", KAKAO_SNS_CLIENT_ID); // 앱 REST API 키
        params.add("redirect_uri", KAKAO_SNS_CALLBACK_URL); // 인가코드가 redirected된 uri
        params.add("code", code); // 인가코드 받기 요청으로 얻은 인가코드

        // --- Heather ---
        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 고정

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        //실제로 요청하기
        //Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
        ResponseEntity<String> response = restTemplate.exchange( // exchange() 메소드로 api 를 호출
                KAKAO_SNS_TOKEN_BASE_URL, // url
                HttpMethod.POST, // crud
                kakaoTokenRequest, // entity
                String.class // 원하는 클래스타입.class
        ); // 호출결과로 http status code, 헤더 정보, 실제 데이터가 존재하는 body 정보를 확인

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody(); // 각종 토큰과 만료시간 return
        }

        return "카카오 로그인 요청 처리 실패";
    }

    @Override
    public String requestSocialData(String token) {
        try {
            String requestURL = KAKAO_SNS_USER_API_URL;
            RestTemplate restTemplate = new RestTemplate(); // Spring HTTP 통신 템플릿, HTTP 요청 후 JSON, String .. 과 같은 응답을 받을 수 있는 템플릿 -> 주로 외부 api를 호출할 때 사용함.

            //HttpHeader 오브젝트 생성
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 고정
            headers.add("Authorization", token);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    requestURL,
                    HttpMethod.GET,
                    request,
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody(); // 각종 토큰과 만료시간 return
            }
            return "FAIL";
        } catch (HttpClientErrorException e) {
            return "UNAUTHORIZED";
        }
    }
}
