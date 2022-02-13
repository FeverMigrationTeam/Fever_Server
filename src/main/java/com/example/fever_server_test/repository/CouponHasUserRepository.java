package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.CouponHasUser;
import com.example.fever_server_test.model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponHasUserRepository extends JpaRepository<CouponHasUser,Long> {

    List<CouponHasUser> findAllByUserUserIdx(Member member);
}
