package com.example.fever_server_test.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    private String videoUrl;

    private String videoTitle;

    // 왜래키 : Stadium <- video 1 : N 단방향 매핑 ( 임시 )
    @ManyToOne
    @JoinColumn(name = "stadium_stadium_idx")
    private Stadium stadium;


    // 왜래키 : Member <- video 1 : N 단방향 매핑 ( 임시 )
    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    private Member member;

    @CreatedDate
    private LocalDateTime videoCreateTime;

    @LastModifiedDate
    private LocalDateTime videoUpdateTime;



}
