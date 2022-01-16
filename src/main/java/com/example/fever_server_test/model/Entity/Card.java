package com.example.fever_server_test.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // Auditing : 감시, 자동으로 시간을 매핑하여 DB 테이블에 넣어줌.
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardIdx;


    // 왜래키 : Card -> Member : 다대일 단방향 ( 임시 )
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private Member member;

    private String cardCompanyName; // 카트회사 이름

    private String cardNumber; // 카드번호

    @CreatedDate
    private LocalDateTime cardCreateTime;

    @LastModifiedDate
    private LocalDateTime cardUpdateTime;


}
