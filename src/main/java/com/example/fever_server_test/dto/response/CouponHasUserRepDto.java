package com.example.fever_server_test.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponHasUserRepDto {

    private Long couponHasUserIdx;
    private int couponUsed; // 쿠폰 사용여부
    private String couponExpirationDate; // 쿠폰 사용기한

}
