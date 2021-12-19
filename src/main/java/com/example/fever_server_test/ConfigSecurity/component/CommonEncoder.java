package com.example.fever_server_test.ConfigSecurity.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// 패스워드 인코더 구현 , @Bean 등록은 스프링 어플리케이션에서 했음.
public class CommonEncoder implements PasswordEncoder {
    private final PasswordEncoder passwordEncoder;

    public CommonEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public CommonEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
