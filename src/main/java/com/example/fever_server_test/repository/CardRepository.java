package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Card;
import com.example.fever_server_test.model.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findAllByMember(Member member);
}
