package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuthRepository extends JpaRepository<Member,Long> {

//    Member findByEmail(String email);

//    void deleteByEmail(String email);
}
