package com.example.fever_server_test.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StadiumRespDto {

    private int stadiumIdx;
    private String stadiumName;
    private String stadiumAddress;
    private String stadiumNumber;
    private String stadiumLatitude;
    private String stadiumLongitute;
    private String stadiumImg;


}

