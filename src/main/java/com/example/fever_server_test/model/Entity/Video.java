package com.example.fever_server_test.model.Entity;


import com.example.fever_server_test.dto.request.VideoUploadReqDto;
import com.example.fever_server_test.model.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "video")
public class Video {//extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoIdx;

    private String videoUrl;

    private String videoTitle;

    // Video -> equipment 다대일 단방향
    @ManyToOne
    @JoinColumn(name = "video_equipment_idx")
    private Equipment equipment;


    // Video -> Member 다대일 단방향 매핑
    @ManyToOne
    @JoinColumn(name = "video_user_idx")
    private Member member;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


     public Video(String videoUrl, String videoTitle, Equipment equipment, Member member){
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.equipment = equipment;
        this.member = member;
    }


}
