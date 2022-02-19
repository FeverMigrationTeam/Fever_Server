package com.example.fever_server_test.service;


import com.example.fever_server_test.dto.request.CompletePaymentsReqDto;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentsService {
    private final Status status;
    private final ResponseMessage message;
    // private final PaymentsRepository paymentsrepository;

    public ResponseEntity completePayments(CompletePaymentsReqDto completePaymentsReqDto){
        System.out.println(completePaymentsReqDto);
        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 아임포트 api 테스트 " + message.SUCCESS, completePaymentsReqDto.getImpUid()), HttpStatus.OK);

    }
}
