package com.example.fever_server_test.model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class CouponHasUserId implements Serializable {

    @Column(name = "coupon_has_user_idx")
    private int couponHasUserIdx;

//    @Column(name = "coupon_coupon_idx")
    @OneToOne
    @JoinColumn(name = "coupon_idx")
    private Coupon couponCouponIdx;

//    @Column(name = "user_user_idx")
    @OneToOne
    @JoinColumn(name = "user_idx")
    private Member userUserIdx;

}
