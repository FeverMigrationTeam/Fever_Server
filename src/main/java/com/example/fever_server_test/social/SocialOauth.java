package com.example.fever_server_test.social;

public interface SocialOauth {

    /**
     * ---- STEP 1 ----
     * 각 Social Login 페이지로 Redirect 처리할 URL Build
     * 사용자로부터 로그인 요청을 받아 Social Login Server 인증용 code 요청
     */
    String getOauthRedirectURL();

    /**
     * ---- STEP 2 ----
     * 카카오 API Server로부터 받은 인가 코드(code)를 활용하여 토큰 받기
     * @param code : 인가 코드
     * @return 카카오 API Server로부터 응답받은 Json 형태의 결과물 string으로 응답
     * */
    String requestAccessToken(String code);

    String requestSocialData(String token);

    default SocialLoginType type(){
        if ( this instanceof GoogleOauth){
            return SocialLoginType.GOOGLE;
        } else if ( this instanceof NaverOauth){
            return SocialLoginType.NAVER;
        } else{
            return SocialLoginType.KAKAO;
        }
    }
}
