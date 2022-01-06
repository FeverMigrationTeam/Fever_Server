package com.example.fever_server_test.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SignInRespDto {

    private String result;
    private String message;
    private String accessToken;
    private String refreshToken;

}
