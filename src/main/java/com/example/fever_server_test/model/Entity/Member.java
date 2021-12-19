package com.example.fever_server_test.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "coupon_coupon_idx")
    private Long couponId;

    private String userName;

    @Column(name = "user_residentregistration_number")
    private String userRegisterNum;

    private String userPhone;

    @CreatedDate
    private LocalDateTime userCreateTime;

    @LastModifiedDate
    private LocalDateTime userUpdateTime;



}
