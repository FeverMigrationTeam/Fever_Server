package com.example.fever_server_test.model.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing : 감시, 자동으로 시간을 매핑하여 DB 테이블에 넣어줌.
@Table(name = "alram")
//@IdClass(Alarm.class) // 식별자 클래스 매핑
public class Alarm implements Serializable { // pk 2개 일 때

    @Id
    private Long alramIdx;

    @Id
    private Long alramUserIdx;

    private String alramTitle;

    private String alramContent;

    @CreatedDate
    private LocalDateTime alramCreatetime;

    @LastModifiedDate
    private LocalDateTime alramUpdatetime;




}
