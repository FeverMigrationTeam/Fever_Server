package com.example.fever_server_test.repository;
import com.example.fever_server_test.model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

}
