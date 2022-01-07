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
public class CouponHasUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int couponHasUserIdx;

    // 왜래키 : 매핑 필요함
    private int couponCouponIdx;

    // 왜래키 : 매핑 필요함
    private int userUserIdx;

    @Column(name = "coupon_has_coupon_expirationdate")
    private String couponExpirationDate; // 쿠폰 사용기한

    @Column(name = "coupon_has_user_isUsed",columnDefinition = "TINYINT",length = 1)
    private int couponIsUsed; // 0 : 사용안함 , 1 : 사용함

    @CreatedDate
    private LocalDateTime couponHasUserCreateTime;

    @LastModifiedDate
    private LocalDateTime couponHasUserUpdateTime;






}
