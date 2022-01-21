package com.example.fever_server_test.member;

import com.example.fever_server_test.model.Entity.Member;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MemberTest {
    LocalDateTime localDateTime = LocalDateTime.now();
    @Test
    @DisplayName("회원가입 테스트 성공")
    void join(){
        Member member = new Member(100, "삼선동손흥민", "01094460770", localDateTime, localDateTime);

        Assertions.assertThat(member.getUserIdx()).isEqualTo();
    }

}
