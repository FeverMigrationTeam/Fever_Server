package com.example.fever_server_test.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @ToString
public class CompletePaymentsReqDto {

    private int impUid;
    private int merchantUid;
}
