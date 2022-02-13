package com.example.fever_server_test.repository;
import com.example.fever_server_test.model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserIdx(int userIdx);
    Optional<Member> findByUserSocialIdx(String userSocialIdx);
}
