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
@IdClass(CouponHasUserId.class) // 식별자 클래스인 CouponHasUserId를 매핑
public class CouponHasUser {

//    @EmbeddedId
//    private CouponHasUserId couponHasUserId;

    @Id
    @Column(name = "coupon_has_user_idx")
    private Long couponHasUserIdx;

    @Id
    @OneToOne
    @JoinColumn(name = "coupon_coupon_idx")
    private Coupon couponCouponIdx;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    private Member userUserIdx;

    @Column(name = "coupon_has_coupon_expirationdate")
    private String couponExpirationDate; // 쿠폰 사용기한

    @Column(name = "coupon_used",columnDefinition = "TINYINT",length = 1)
    private int couponUsed; // 0 : 사용안함 , 1 : 사용함

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;






}
