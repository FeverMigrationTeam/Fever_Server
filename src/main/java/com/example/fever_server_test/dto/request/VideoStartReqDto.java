package com.example.fever_server_test.dto.request;

import com.example.fever_server_test.social.SocialLoginType;
import lombok.Data;

@Data
public class VideoStartReqDto {
    Long equipmentId;
    String loginType;
}
