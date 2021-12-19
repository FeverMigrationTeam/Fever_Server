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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing : 감시, 자동으로 시간을 매핑하여 DB 테이블에 넣어줌.
@Table(name = "alram")
public class Alarm { // pk 2개 일 때

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alaramId;

    @ManyToOne
    @JoinColumn(name="user_idx")
    private Member memberList;

    private String alramTitle;

    private String alramContent;

    @CreatedDate
    private LocalDateTime alramCreatetime;

    @LastModifiedDate
    private LocalDateTime alramUpdatetime;

}
