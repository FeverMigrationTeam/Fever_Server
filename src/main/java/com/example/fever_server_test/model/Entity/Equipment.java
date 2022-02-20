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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentIdx;

    // 왜래키 : equipment -> Stadium : 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_stadium_idx")
    private Stadium stadium;

    private String equipmentQr;

    // db상의 tinyint형은 columnDefinition으로 자료형을 명시해주는 것이 좋다고 한다. 걍 습관처럼 하라고들 함.. 에러 날수도 있어서
    @Column(name = "equipment_service_state",columnDefinition = "TINYINT",length = 1)
    private int equipmentServiceState;

    @Column(name = "equipment_state",columnDefinition = "TINYINT",length = 1)
    private int euipmentState;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;



}
