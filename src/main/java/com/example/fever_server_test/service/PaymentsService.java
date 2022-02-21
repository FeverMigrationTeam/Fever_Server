package com.example.fever_server_test.service;


import com.example.fever_server_test.dto.request.CompletePaymentsReqDto;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsService {
    private final Status status;
    private final ResponseMessage message;
    // private final PaymentsRepository paymentsrepository;
//    private final RestTemplate restTemplate;

    public ResponseEntity completePayments(CompletePaymentsReqDto completePaymentsReqDto) throws IOException, JSONException {
        /* ========== 결제번호(imp_uid) , 주문번호(merchant_uid) 추출하기 =========== */
        System.out.println("결제 성공 : " + completePaymentsReqDto);
        String impUid = completePaymentsReqDto.getImpUid();
        String merchantUid = completePaymentsReqDto.getMerchantUid();
        System.out.println("merchantUid = " + merchantUid);
        System.out.println("impUid = " + impUid);


        String urlString = "https://api.iamport.kr/users/getToken";

        /* ========== 액세스 토큰(access token) 발급받기 =========== */
        System.out.println("-----befor POST-----");

        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String accessToken = "";

        connection.setRequestMethod("POST");
        //header 설정
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json"); // 아임포트 서버로부터 받을 DATA 타입
        //Data 설정
        connection.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨줌.

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("imp_key", "3096555515632437");
        jsonObject.put("imp_secret", "a4f1d426590c882dbf6bc43906c9f0271bd9e7a0025cbcfe685ae49d719cb8642cc259de7c2de27e");

        // 아임포트 서버로 request
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(jsonObject.toString());
        bw.flush();
        bw.close();

        // response
        int responseCode = connection.getResponseCode(); // 응답코드
        System.out.println("responseCode = " + responseCode);

        if (responseCode == 200) { // success
            System.out.println("결제 성공");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            System.out.println();
            accessToken = sb.substring(sb.indexOf("access_token") + 15, sb.indexOf("now") - 3);
            System.out.println("result: " + sb.toString());
            System.out.println("accessToken: " + accessToken);

        } else { // 실패 : 응답코드 400, 404
            System.out.println(connection.getResponseMessage());
        }


        System.out.println("----after POST------");

        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 아임포트 api 테스트 " + message.SUCCESS, accessToken), HttpStatus.OK);

    }
}
