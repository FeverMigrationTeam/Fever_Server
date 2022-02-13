package com.example.fever_server_test.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class CouponHasUserId implements Serializable {

    @Column(name = "coupon_has_user_idx")
    private Long couponHasUserIdx;

//    @Column(name = "coupon_coupon_idx")
    @OneToOne
    @JoinColumn(name = "coupon_coupon_idx")
    private Coupon couponCouponIdx;

//    @Column(name = "user_user_idx")
    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    private Member userUserIdx;

}
