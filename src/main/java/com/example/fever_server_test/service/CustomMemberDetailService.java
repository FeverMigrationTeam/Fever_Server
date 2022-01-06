package com.example.fever_server_test.service;

import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.OuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {

    private final OuthRepository outhRepository;

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        return outhRepository.findByEmail(userEmail);
    }

    public UserDetails findByEmail(String email) {
        return outhRepository.findByEmail(email);
    }

    public int signUpUser(Member member) {
        if (outhRepository.findByEmail(user.getEmail()) == null) {
            outhRepository.save(user);
            return 1; // 회원가입 성공시
        } else
            return -1; // 회원가입 실패시
    }

    public void deletUser(String email) { // 유저 삭제 ** 조금 위험함 사용할 때 다시 코드 수정하기
        outhRepository.deleteByEmail(email);

    }

    public Optional<User> findById(Long userId) {
        return outhRepository.findById(userId);
    }

    public void save(Member member) {
        if (member != null)
            outhRepository.save(member);
    }
}

