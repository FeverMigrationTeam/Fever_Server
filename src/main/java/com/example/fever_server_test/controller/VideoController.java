package com.example.fever_server_test.controller;

import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ResponseBody
    @GetMapping("/")
    public String greeting() {
        return videoService.greet();
    }

    @PostMapping("/start")
    public ResponseEntity<NoDataResponse> startVideo(@RequestHeader("Authorization") String token, VideoStartReqDto videoStartReqDto) {
        return videoService.startVideo(token, videoStartReqDto);
    }
}
