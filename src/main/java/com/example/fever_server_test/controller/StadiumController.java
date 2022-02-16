package com.example.fever_server_test.controller;


import com.example.fever_server_test.service.StadiumService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    /* 모든 구장조회 : selectStadium --Tony */
    @GetMapping()
    public ResponseEntity selectStadium(){
        return stadiumService.selectStadium();
    }

    /* 구장 검색 : searchStadium --Tony */
    @GetMapping("/{stadiumName}")
    public ResponseEntity searchStadium(@PathVariable String stadiumName){
        return stadiumService.searchStadium(stadiumName);
    }

    /* */
}
