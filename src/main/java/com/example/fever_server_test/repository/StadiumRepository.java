package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium,Long> {

// Optional<Stadium> findByStadiumName(String stadiumName);
 List<Stadium> findByStadiumNameContaining(String stadiumName);

}
