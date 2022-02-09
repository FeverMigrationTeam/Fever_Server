package com.example.fever_server_test.service;


import com.example.fever_server_test.social.SocialLoginType;
import com.example.fever_server_test.social.SocialOauth;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

//    @RequiredArgsConstructor를 통해 SocialOauth 타입의 객체들이 List 형태로 Injection 되도록 필드를 List 타입으로 선언
//      SocialLoginType에 맞는 SocialOauth 객체를 반환하는 findSocialOauthByType 메소드 생성하여 각 request, requestAccessToken 메소드에서
//      SocialLoginType에 맞는 SocialOauth 클래스를 findSocialOauthByType 함수를 통해 초기화


        public String getKakaoAccessToken(String code) {
            String access_Token = "";
            String refresh_Token = "";
            String reqURL = "https://kauth.kakao.com/oauth/token";

            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                StringBuilder sb = new StringBuilder();
                sb.append("grant_type=authorization_code");
                sb.append("&client_id=e4a81d5a6acbda948310e08e2eafc123"); // TODO REST_API_KEY 입력
                sb.append("&redirect_uri=http://localhost:9000/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
                sb.append("&code=" + code);
                bw.write(sb.toString());
                bw.flush();

                //결과 코드가 200이라면 성공
                int responseCode = conn.getResponseCode();
                System.out.println("responseCode : " + responseCode);

                //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }
                System.out.println("response body : " + result);

                //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result);

                access_Token = element.getAsJsonObject().get("access_token").getAsString();
                refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

                System.out.println("access_token : " + access_Token);
                System.out.println("refresh_token : " + refresh_Token);

                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return access_Token;
        }

        public void request(SocialLoginType socialLoginType) {
            SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType); // ex) KAKAO
            String redirectURL = socialOauth.getOauthRedirectURL();
            System.out.println("Service redirectURL >>" + redirectURL);
            try {
                response.sendRedirect(redirectURL); // redirectURL return
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String requestAccessToken(SocialLoginType socialLoginType, String code) {
            SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
            return socialOauth.requestAccessToken(code);
        }

        private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
            return socialOauthList.stream()
                    .filter(x -> x.type() == socialLoginType)
                    .findFirst() // filter 조건에 일치하는 가장 앞에 있는 element 1개를 Optional 로 return ex) KAKAO return
                    .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
        }

    }
