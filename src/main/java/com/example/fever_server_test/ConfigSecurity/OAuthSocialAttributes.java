package com.example.fever_server_test.ConfigSecurity;


import com.pet.comes.model.Entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthSocialAttributes {

    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map

    private String nameAttributeKey;
    private String nickname;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthSocialAttributes(String nickname, Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

//    public static OAuthSocialAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
    //(new!) kakao
//        if("kakao".equals(registrationId)){
//            return ofKakao("id", attributes);
//        }
    // naver
//        if("naver".equals(registrationId)){
//            return ofNaver("id", attributes);
//        }
//         google
//        return ofGoogle(userNameAttributeName, attributes);
//
//    }


    private static OAuthSocialAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile"); //profile_nickname
        Map<String, Object> kakaoNickName = (Map<String, Object>) kakaoAccount.get("profile_nickname"); //profile_nickname

        return OAuthSocialAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
//                .picture((String) kakaoProfile.get("profile_image_url"))
                .nickname((String) kakaoNickName.get(""))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        User user = new User();


        return user;

    }
}
