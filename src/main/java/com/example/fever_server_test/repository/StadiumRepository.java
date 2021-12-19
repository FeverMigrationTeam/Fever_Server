package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium,Long> {

}
