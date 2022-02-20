package com.example.fever_server_test.service;


import com.example.fever_server_test.dto.request.CompletePaymentsReqDto;
import com.example.fever_server_test.dto.response.PaymentsTokenRespDto;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentsService {
    private final Status status;
    private final ResponseMessage message;
    // private final PaymentsRepository paymentsrepository;
//    private final RestTemplate restTemplate;

    public ResponseEntity completePayments(CompletePaymentsReqDto completePaymentsReqDto) {
        System.out.println("---결제 성공---" + completePaymentsReqDto);
//        String response = restTemplate.getForObject(baseUrl,String.class);

        // 결제번호(imp_uid) , 주문번호(merchant_uid) 추출하기 : POST 요청하기

        String url = "https://api.iamport.kr/users/getToken";

        String impUid = completePaymentsReqDto.getImpUid();
        String merchantUid = completePaymentsReqDto.getMerchantUid();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("imp_uid", impUid);
        parameters.add("merchant_uid", merchantUid);
        ResponseEntity<String> response = new RestTemplate().postForEntity(url, parameters, String.class);
        String accessToken = response.getBody();
        System.out.println("access_token = " + accessToken);


        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 아임포트 api 테스트 " + message.SUCCESS, completePaymentsReqDto.getImpUid()), HttpStatus.OK);

    }
}
