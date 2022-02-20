package com.example.fever_server_test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsTokenRespDto {
    private String imp_key;
    private String imp_secret;
}
