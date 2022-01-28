package com.example.fever_server_test.controller;

import com.example.fever_server_test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, NAVER, KAKAO)
     **/
    @RequestMapping(value = "/login/ouath/kakao")
    public ResponseEntity getKakoOuthUrl(@RequestMapping){

    }

}
