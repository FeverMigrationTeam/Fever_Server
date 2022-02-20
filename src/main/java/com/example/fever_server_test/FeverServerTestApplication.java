package com.example.fever_server_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // ex) Timestamped 자동생성 도와줌
public class FeverServerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeverServerTestApplication.class, args);
	}

}
