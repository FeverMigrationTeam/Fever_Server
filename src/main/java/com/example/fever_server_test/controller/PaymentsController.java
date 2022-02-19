package com.example.fever_server_test.controller;

import com.example.fever_server_test.dto.request.CompletePaymentsReqDto;
import com.example.fever_server_test.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;

@RestController
@RequiredArgsConstructor // final 선언 관련 생성자 주입
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;


    @PostMapping("/complete")
    public ResponseEntity completePayments(@RequestBody CompletePaymentsReqDto completePaymentsReqDto) {
        return paymentsService.completePayments(completePaymentsReqDto);
    }
}
