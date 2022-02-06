package com.example.fever_server_test.service;


import com.example.fever_server_test.social.SocialLoginType;
import com.example.fever_server_test.social.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

//    @RequiredArgsConstructor를 통해 SocialOauth 타입의 객체들이 List 형태로 Injection 되도록 필드를 List 타입으로 선언
//      SocialLoginType에 맞는 SocialOauth 객체를 반환하는 findSocialOauthByType 메소드 생성하여 각 request, requestAccessToken 메소드에서
//      SocialLoginType에 맞는 SocialOauth 클래스를 findSocialOauthByType 함수를 통해 초기화


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
