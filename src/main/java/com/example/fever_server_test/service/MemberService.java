package com.example.fever_server_test.service;

import com.example.fever_server_test.components.UserId;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final Status status;
    private final ResponseMessage message;
    private final MemberRepository memberRepository;

    public UserId verifyUser(String loginType, String token) {
        return new UserId("KAKAO", "00000000");
    }
}
