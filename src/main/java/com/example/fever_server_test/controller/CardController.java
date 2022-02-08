package com.example.fever_server_test.controller;


import com.example.fever_server_test.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 선언 관련 생성자 주입
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    @GetMapping("/test")
    public void testApi(){
        System.out.println("testAPI ---");
    }
}
