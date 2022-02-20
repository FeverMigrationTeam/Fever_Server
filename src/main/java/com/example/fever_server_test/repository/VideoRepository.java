package com.example.fever_server_test.repository;

import com.example.fever_server_test.dto.response.MyAllVideoRespProjection;
import com.example.fever_server_test.model.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

    @Query("select v.videoIdx as videoIdx, v.videoUrl AS videoUrl, v.videoTitle AS videoTitle, " +
            "s.stadiumName as stdiumName, v.createdAt as createdAt " +
            "from Video v inner join Equipment e on  e.equipmentIdx = v.equipment.equipmentIdx left outer join Stadium s on e.stadium.stadiumIdx = s.stadiumIdx " +
            "where v.member.userIdx= :userId")
    List<MyAllVideoRespProjection> findAllByMemberOrderByVideoIdxQuery(@Param("userId") Long userId);

    Optional<Video> findByVideoTitle(String videoTitle);
}


