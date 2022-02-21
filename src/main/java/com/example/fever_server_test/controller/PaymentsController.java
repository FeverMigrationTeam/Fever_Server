package com.example.fever_server_test.controller;

import com.example.fever_server_test.dto.request.CompletePaymentsReqDto;
import com.example.fever_server_test.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor // final 선언 관련 생성자 주입
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;


    /* 아임포트 결제 : 결제번호(imp_uid) , 주문번호(merchant_uid) 추출하기 */
//    @PostMapping("/complete")
    @RequestMapping(value = "/complete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity completePayments(@RequestBody CompletePaymentsReqDto completePaymentsReqDto) throws IOException, JSONException {
        return paymentsService.completePayments(completePaymentsReqDto);
    }
}
