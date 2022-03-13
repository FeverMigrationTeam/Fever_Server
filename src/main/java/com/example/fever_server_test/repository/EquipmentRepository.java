package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    Optional<Equipment> findById(Long equipmentIdx);

}
