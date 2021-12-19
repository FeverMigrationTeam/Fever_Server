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
    private Long cardIdx;

    private Long couponCouponIdx; // ** db에 왜래키로 등록되어 있음

    private Long userUserIdx; // ** db에 왜래키로 등록되어 있음

    private String couponHasCouponExpirationdate; // 사용 기한

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1) // DBMS의 테이블과 매핑시 오류방지
    private int couponHasUserIsUsed;

    @CreatedDate
    private LocalDateTime couponHasUserUpdateTime;

    @LastModifiedDate
    private LocalDateTime couponHasUserCreateTime;


}
