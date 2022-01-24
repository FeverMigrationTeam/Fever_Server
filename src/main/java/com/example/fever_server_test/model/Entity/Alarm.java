package com.example.fever_server_test.model.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing : 감시, 자동으로 시간을 매핑하여 DB 테이블에 넣어줌.
@Table(name = "alram")
@IdClass(AlarmId.class) // 식별자 클래스인 AlarmId를 매핑해줌.
public class Alarm { // pk 2개일 때 설정해줘야됨.

    @Id
    @Column(name = "alram_idx")
    private int alarmIdx;

    @Id
    @Column(name = "alram_user_idx")
    private int alarmUserIdx;

    private String alramTitle;

    private String alramContent;

    @CreatedDate
    private LocalDateTime alramCreatetime;

    @LastModifiedDate
    private LocalDateTime alramUpdatetime;

}
