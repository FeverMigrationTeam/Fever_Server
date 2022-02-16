package com.example.fever_server_test.controller;


import com.example.fever_server_test.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    /* 보유 이용권 조회 : selectCoupon2 --Tony */
    @GetMapping("/{userId}")
    public ResponseEntity selectCoupon(@PathVariable Long userId){
        return couponService.selectCoupon(userId);
    }
}
