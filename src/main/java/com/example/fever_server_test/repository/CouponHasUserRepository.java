package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.CouponHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponHasUserRepository extends JpaRepository<CouponHasUser,Long> {

}
