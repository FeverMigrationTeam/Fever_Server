package com.example.fever_server_test.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyCardRespDto {

    private int cardIdx;
    private int userUserIdx;
    private String cardCompanyName;
    private String cardNumber;
    private String createdAt;
    private String modifiedAt;

}
