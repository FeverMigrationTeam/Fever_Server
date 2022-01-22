package com.example.fever_server_test.equipment;

import com.example.fever_server_test.repository.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EquipmentTest {

    @BeforeEach
    public void beforeEach(){
        System.out.println("---- @Test 이전에 beforeEach() 실행 ----");
    }

    @Test
    @DisplayName("---- @Test , @DisplayName 으로 출력하기 ----")
    void join(){

    }
}
