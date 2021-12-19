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

    private String stadiumName;

    private String stadiumAddress;

    @Column(name = "stadium_operating_hours")
    private String operatingHours;

    private String stadiumNumber;

    private String stadiumLatitude;

    private String stadiumLongtitude;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1) // DBMS의 테이블과 매핑시 오류방지
    private int stadiumTroubleState;

    private String stadiumQR;

    private String stadiumImg;

    @CreatedDate
    private LocalDateTime stadiumCreateTime;

    @LastModifiedDate
    private LocalDateTime stadiumUpdateTime;


}
