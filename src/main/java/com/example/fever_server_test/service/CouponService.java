package com.example.fever_server_test.service;

import com.example.fever_server_test.dto.response.CouponHasUserRespDto;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.CouponHasUserRepository;
import com.example.fever_server_test.repository.CouponRepository;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final Status status;
    private final ResponseMessage message;
    private final CouponRepository couponRepository;
    private final CouponHasUserRepository couponHasUserRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    /* 보유 이용권 : selectCoupon2 --Tony */
    public ResponseEntity selectCoupon(@PathVariable Long userId) {
        Optional<Member> byId = memberRepository.findById(userId);
        if (!byId.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID), HttpStatus.OK);

        Member member = byId.get();
        List<CouponHasUserRespDto> allByUserUserIdx = couponHasUserRepository.findAllByUserUserIdx(member)
                .stream()
                .map(couponHasUser -> modelMapper.map(couponHasUser, CouponHasUserRespDto.class))
                .collect(Collectors.toList());

        if (allByUserUserIdx.isEmpty())
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA + " 이용권이 없습니다."), HttpStatus.OK);


        return new ResponseEntity(DataResponse.response(status.SUCCESS,
                message.SUCCESS_SELECT_COUPON, allByUserUserIdx), HttpStatus.OK);

    }
}
