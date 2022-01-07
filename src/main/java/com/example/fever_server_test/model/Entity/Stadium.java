package com.example.fever_server_test.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stadium")
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stadiumId;

    private String stadiumName; // 구장 이름

    private String stadiumAddress; // 구장 주소

    @Column(name = "stadium_operating_hours")
    private String operatingHours; // 운영시간

    private String stadiumNumber; // 전화번호

    private String stadiumLatitude; // 위도

    private String stadiumLongtitude; // 경도

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1) // DBMS의 테이블과 매핑시 오류방지
    private int stadiumTroubleState; // 고장유무

    private String stadiumQR; // QR 정보

    private String stadiumImg;

    @CreatedDate
    private LocalDateTime stadiumCreateTime;

    @LastModifiedDate
    private LocalDateTime stadiumUpdateTime;


}
