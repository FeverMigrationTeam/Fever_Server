package com.example.fever_server_test.service;

import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.repository.VideoRepository;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
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
        String body = oauthService.requestSocialData(videoStartReqDto.getLoginType(), token);

        return new ResponseEntity<NoDataResponse>(
                NoDataResponse.response(200, "SUCCESS"), HttpStatus.OK);
    }
}
