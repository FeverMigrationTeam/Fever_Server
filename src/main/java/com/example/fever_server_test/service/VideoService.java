package com.example.fever_server_test.service;

import com.example.fever_server_test.dto.request.VideoUploadReqDto;
import com.example.fever_server_test.dto.response.MyAllVideoRespProjection;
import com.example.fever_server_test.model.Entity.Equipment;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.model.Entity.Video;
import com.example.fever_server_test.repository.EquipmentRepository;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.repository.VideoRepository;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final Status status;
    private final ResponseMessage message;
    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;
    private final EquipmentRepository equipmentRepository;
    private final ModelMapper modelMapper;


    /* 비디오 조회 (리갈라 영상함) : selectMyVideo --Tony */
    public ResponseEntity selectMyVideo(Long userId) {
        Optional<Member> byId = memberRepository.findById(userId);

        if (!byId.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID), HttpStatus.NOT_FOUND);

        List<MyAllVideoRespProjection> allByMemberOrderByVideoIdxQuery = videoRepository.findAllByMemberOrderByVideoIdxQuery(byId.get().getUserIdx())
                .stream()
                .map(video -> modelMapper.map(video, MyAllVideoRespProjection.class))
                .collect(Collectors.toList());

        if (allByMemberOrderByVideoIdxQuery.isEmpty())
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA + " 비디오가 없습니다. "), HttpStatus.OK);

        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 나의 비디오 조회 " + message.SUCCESS, allByMemberOrderByVideoIdxQuery), HttpStatus.OK);
    }

    /* 비디오 저장 : insertVideo --Tony */
    public ResponseEntity insertVideo(VideoUploadReqDto videoUploadReqDto) {

        // user(member) 검사
        Optional<Member> byId = memberRepository.findById(videoUploadReqDto.getVideoUserIdx());
        if (!byId.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID + " videoUserId"), HttpStatus.NOT_FOUND);

        // equipment 검사
        Optional<Equipment> byId1 = equipmentRepository.findById(videoUploadReqDto.getVideoEquipmentIdx());
        if (!byId1.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID + " videoEquipmentIdx"), HttpStatus.NOT_FOUND);

        // videoTitle 중복 검사
        Optional<Video> byVideoTitle = videoRepository.findByVideoTitle(videoUploadReqDto.getVideoTitle());
        if (byVideoTitle.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.EXISTED_TITLE, message.EXISTED_TITLE + " videoTitle"), HttpStatus.NOT_FOUND);

        String videoUrl = videoUploadReqDto.getVideoUrl();
        String videoTitle = videoUploadReqDto.getVideoTitle();
        Equipment equipment = byId1.get();
        Member member = byId.get();

        Video video = new Video(videoUrl,videoTitle, equipment, member); // timestamp 안찍힘
//        Video video = new Video();
//        video.setVideoUrl(videoUrl);
//        video.setVideoTitle(videoTitle);
//        video.setEquipment(equipment);
//        video.setMember(member);

        // DB저장
        videoRepository.save(video);

        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 비디오 업로드 " + message.SUCCESS, video.getVideoIdx()), HttpStatus.OK);

    }

}
