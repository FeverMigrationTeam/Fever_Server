package com.example.fever_server_test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoUploadReqDto {

    private String videoUrl;
    private String videoTitle;
    private Long videoEquipmentIdx;
    private Long videoUserIdx;

}
