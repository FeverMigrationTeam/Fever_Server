package com.example.fever_server_test.model.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


// Alarm 엔티티 복합키 관련 설정
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable  // 복합키 매핑시
@Data // equals, hashCode 오버라이드
public class AlarmId implements Serializable {

    @Column(name = "alram_idx")
    private int alramIdx;

//    @Column(name = "alram_user_idx")
    @ManyToOne  // Alarm -> User 다대일 단방향
    @JoinColumn(name="user_idx") // user 엔티티의 테이블상 id 이름
    private Member member;



}
