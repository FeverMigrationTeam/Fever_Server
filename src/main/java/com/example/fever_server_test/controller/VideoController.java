package com.example.fever_server_test.controller;

import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.service.VideoService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity startVideo(@RequestHeader("Authorization") String token, VideoStartReqDto videoStartReqDto) throws Exception {
        return videoService.startVideo(token, videoStartReqDto);
    }

    /* 비디오 조회 (리갈라 영상함) : selectMyVideo --Tony */
    @GetMapping("/{userId}")
    public ResponseEntity selectMyVideo(@PathVariable Long userId){
        return videoService.selectMyVideo(userId);
    }

}


