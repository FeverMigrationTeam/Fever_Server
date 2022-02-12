package com.example.fever_server_test.controller;

import com.example.fever_server_test.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
