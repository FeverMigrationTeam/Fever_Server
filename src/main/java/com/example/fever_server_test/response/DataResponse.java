package com.example.fever_server_test.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResponse<T> {
    private int status;
    private String responseMessage;
    private T response;

    public DataResponse(final int status, final String responseMessage, T data) {
        this.status = status;
        this.responseMessage = responseMessage;
        this.response = data;
    }

    public static<T> DataResponse<T> response(final int status, final String responseMessage, T data) {
        return DataResponse.<T>builder()
                .status(status)
                .responseMessage(responseMessage)
                .response(data)
                .build();
    }
}
