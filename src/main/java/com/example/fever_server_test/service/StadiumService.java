package com.example.fever_server_test.service;


import com.example.fever_server_test.repository.StadiumRepository;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final Status status;
    private final ResponseMessage message;
    public final StadiumRepository stadiumRepository;
}
