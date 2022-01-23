package com.example.fever_server_test.service;

import com.example.fever_server_test.repository.CardRepository;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 선언된 변수들의 생성자 만들어줌
public class CardService {

        private final Status status;
        private final ResponseMessage message;
        private final CardRepository cardRepository;



}
