package com.example.fever_server_test.controller;


import com.example.fever_server_test.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    /* 모든 알람조회 : selectAlram --Tony */
    @GetMapping("/{userId}")
    public ResponseEntity selectAllAlarm(@PathVariable Long userId) {
        return alarmService.selectAllAlarm(userId);
    }

}
