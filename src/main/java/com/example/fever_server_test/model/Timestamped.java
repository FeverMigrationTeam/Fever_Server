package com.example.fever_server_test.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // Entity 클래스들이 해당 추상 클래스를 상속할 경우 @CreatedDate.. 등을 column으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing : 감시, 자동으로 시간을 매핑하여 DB 테이블에 넣어줌.
public abstract class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
