package com.example.fever_server_test.ConfigSecurity;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;


// Jwt 가 유효한 토큰인지 인증하기 위한 filter
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;
//    private CustomUserDetailService customUserDetailService;

    // Jwt Provier 주입
    public JwtAuthenticationFilter( JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;

    }

    // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록합니다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HashMap<String, String> tokens = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        System.out.println("----token get---- ");
        String accesstoken = tokens.get("accesstoken");
        String refreshtoken = tokens.get("refreshtoken");

        if (accesstoken != null) { // access token 확인
            if (jwtTokenProvider.validateToken(accesstoken)) { // token이 유효하면
                System.out.println("------ valid token -----");
                System.out.println("accesstoken : " + tokens.get("accesstoken"));
                System.out.println("refreshtoken : " + tokens.get("refreshtoken"));

                Authentication auth = jwtTokenProvider.getAuthentication(accesstoken);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}