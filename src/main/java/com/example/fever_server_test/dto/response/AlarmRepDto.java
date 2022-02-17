package com.example.fever_server_test.dto.response;

import com.example.fever_server_test.model.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmRepDto {

    private Long alarmIdx;
    private String alarmTitle;
    private String alarmContent;
    private String createdAt;

}
