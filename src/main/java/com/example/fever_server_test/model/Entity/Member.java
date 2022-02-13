package com.example.fever_server_test.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class Member { // 나중에 Spring Security 관련해서 User 라는 객체를 사용할 일이 생기기 때문에 User라는 엔티티 명을 사용하지 않음.

    @Id
    @Column(name = "user_idx") // PK에는 되도록이면 @Column 어노테이션 달아주기 -> 매핑이 잘안되는경우가 있음.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(name = "user_social_idx")
    private String userSocialIdx;

    private String userName;

    @Column(name = "user_residentregistration_number")
    private String userRegiNum;

    @CreatedDate
    private LocalDateTime userCreateTime;

    @LastModifiedDate
    private LocalDateTime userUpdateTime;


    /**
     * 테스트용*/
    public Member(int idx, String userName, String userPhone, String userSocialIdx) {
    }
}
