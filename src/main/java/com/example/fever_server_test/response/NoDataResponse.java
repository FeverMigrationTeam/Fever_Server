package com.example.fever_server_test.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoDataResponse {
    private int status;
    private String responseMessage;

    public NoDataResponse(final int status, final String responseMessage) {
        this.status = status;
        this.responseMessage = responseMessage;
    }

    public static NoDataResponse response(final int status, final String responseMessage) {
        return NoDataResponse.builder()
                .status(status)
                .responseMessage(responseMessage)
                .build();
    }
}
