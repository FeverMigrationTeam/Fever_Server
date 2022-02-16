package com.example.fever_server_test.controller;


import com.example.fever_server_test.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 선언 관련 생성자 주입
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @GetMapping("/{userId}")
    public ResponseEntity selectAllCards(@PathVariable Long userId) {
        return cardService.selectAllCards(userId);
    }


}
