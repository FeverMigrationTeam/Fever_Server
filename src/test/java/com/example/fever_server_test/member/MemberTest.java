package com.example.fever_server_test.member;

import com.example.fever_server_test.model.Entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MemberTest {
    LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    @DisplayName("회원가입 테스트 성공")
    void join(){
        Member member = new Member(100, "삼선동손흥민", "01094460770", null);

        Assertions.assertThat(member.getUserIdx()).isEqualTo(100);
    }

    @Test
    void memberFindTest() throws Exception {

        Member member = new Member(27, "admin", "010########", "211909239");
        member.setUserName("admin");
        Assertions.assertThat(member.getUserName()).isEqualTo("admin");
    }
}
