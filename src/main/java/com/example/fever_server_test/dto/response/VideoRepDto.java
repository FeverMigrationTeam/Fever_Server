package com.example.fever_server_test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoRepDto {

    private Long videoIdx;
    private String videoUrl;
    private String videoTitle;
    private String stadiumName;
    private String createdAt;

}
