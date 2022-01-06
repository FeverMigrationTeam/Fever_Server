package com.example.fever_server_test.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInReqDto {

    private String email;
    private String password;
}
