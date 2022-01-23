package com.example.fever_server_test.controller;

import com.example.fever_server_test.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("video")
public class VideoController {
    private final VideoService videoService;

}
