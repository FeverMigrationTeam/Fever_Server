package com.example.fever_server_test.video;

import com.example.fever_server_test.controller.VideoController;
import com.example.fever_server_test.service.OauthService;
import com.example.fever_server_test.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class VideoTest {

    @Autowired
    private VideoController videoController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Autowired
    private OauthService oauthService;

    @Test
    public void contextLoads() {
        assertThat(videoController).isNotNull();
    }
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        String passTest = "서비스 테스트 통과";
        when(videoService.greet()).thenReturn(passTest);
        this.mockMvc.perform(get("/video/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(passTest)));
    }
}
