package com.example.fever_server_test.ConfigSecurity;

import com.example.fever_server_test.service.CustomMemberDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/
@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

    @Value("spring.jwt.secret") //  @Value application.yml 에 있는 값 가져오기
    private String secretKey;

    //    private long tokenValidMilisecond = 1000L * 60 * 60; // 토큰 유효기간 : 1시간.
    private long tokenValidMilisecond = 1000L * 60 * 60 * 24 ; // 24 시간 ( 하루 )
    private long refreshtokenMIlisecond = 60 * 60 * 24 * 7 * 1000L; // 1주일

    //    private final UserDetailsService userDetailsService;
    private final CustomMemberDetailService customMemberDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); // userPK(email)을 기준으로 회원을 구분하겠다. : claim정보에는 토큰에 부가적으로 실어 보낼 정보를 세팅할 수 있음.
        claims.put("roles", roles); //roles는 user 엔티티에 있는 칼럼
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }
//
    // Jwt 토큰 생성
    public String createRefreshToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk); // userPK(email)을 기준으로 회원을 구분하겠다. : claim정보에는 토큰에 부가적으로 실어 보낼 정보를 세팅할 수 있음.
//        claims.put("roles", roles); //roles는 user 엔티티에 있는 칼럼
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + refreshtokenMIlisecond)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    // Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customMemberDetailService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 파싱 : "accesstoken, refreshtoken: jwt토큰"
    public HashMap<String, String> resolveToken(HttpServletRequest req) {
        // **** 유효성 검사 추가하기 ******
        HashMap<String, String> tokens = new HashMap<>();
        // accesstoken
        String tmp = req.getHeader("accesstoken");
        tokens.put("accesstoken", tmp);
        // refreshtoken
        tmp = req.getHeader("refreshtoken");
        tokens.put("refreshtoken", tmp);

        return tokens;
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}