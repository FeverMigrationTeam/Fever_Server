package com.example.fever_server_test.controller;

import com.example.fever_server_test.dto.request.VideoUploadReqDto;
import com.example.fever_server_test.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {
    private final VideoService videoService;

    /* 비디오 조회 (리갈라 영상함) : selectMyVideo --Tony */
    @GetMapping("/{userId}")
    public ResponseEntity selectMyVideo(@PathVariable Long userId){
        return videoService.selectMyVideo(userId);
    }

    /* 비디오 저장 : insertVideo --Tony */
    @PostMapping()
    public ResponseEntity insertVideo(@RequestBody VideoUploadReqDto videoUploadReqDto){
        return videoService.insertVideo(videoUploadReqDto);
    }

}


