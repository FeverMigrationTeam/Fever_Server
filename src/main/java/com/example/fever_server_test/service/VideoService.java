package com.example.fever_server_test.service;

import com.example.fever_server_test.components.UserId;
import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.repository.VideoRepository;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import com.example.fever_server_test.social.SocialLoginType;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final Status status;
    private final ResponseMessage message;
    private final VideoRepository videoRepository;

    @Autowired
    private OauthService oauthService;

    public String greet() {
        return "Hello, World";
    }

    public ResponseEntity<NoDataResponse> startVideo(String token, VideoStartReqDto videoStartReqDto) {
        String loginType = videoStartReqDto.getLoginType();
        UserId userId;
        if (!loginType.equals("PHONE")) {
            SocialLoginType socialLoginType = SocialLoginType.valueOf(loginType);
            String body = oauthService.requestSocialData(socialLoginType, token);
            JSONObject jsonObject = new JSONObject(body);
            userId = new UserId(loginType, jsonObject.get("id"));
        } else { userId = new UserId(loginType, 0); }

        // 현재는 소셜로그인만 존재한다 가정. 만일 userIdx도 사용한다 하면, Integer 전환 관련 구문 필요

        return new ResponseEntity<NoDataResponse>(
                NoDataResponse.response(200, "SUCCESS"), HttpStatus.OK);
    }
}
