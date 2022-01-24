package com.example.fever_server_test.repository;

import com.example.fever_server_test.model.Entity.Alarm;
import com.example.fever_server_test.model.Entity.AlarmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, AlarmId> { // Alaram Entity가 복합키를 가지고 있기 때문에 식별자 클래스를 기본키가 들어가야 되는 부분에 넣어주기

}
