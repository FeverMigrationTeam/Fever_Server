package com.example.fever_server_test.service;


import com.example.fever_server_test.model.Entity.Equipment;
import com.example.fever_server_test.objects.UserId;
import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.repository.EquipmentRepository;
import com.example.fever_server_test.repository.VideoRepository;

import com.example.fever_server_test.dto.request.VideoUploadReqDto;
import com.example.fever_server_test.dto.response.MyAllVideoRespProjection;
import com.example.fever_server_test.model.Entity.Equipment;

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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final Status status;
    private final ResponseMessage message;
    private final VideoRepository videoRepository;

    private final EquipmentRepository equipmentRepository;
    private final MemberRepository memberRepository;
    private final EquipmentRepository equipmentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    private OauthService oauthService;

    @Autowired
    private MemberService memberService;

    public String greet() {
        return "Hello, World";
    }

    public ResponseEntity startVideo(String token, VideoStartReqDto videoStartReqDto) throws Exception {
        String loginType = videoStartReqDto.getLoginType();
        Long userId;
        try {
            userId = memberService.verifyUser(loginType, token);
        } catch (Exception e) {
            return new ResponseEntity(
                NoDataResponse.response(401, "UNAUTHORIZED"), HttpStatus.UNAUTHORIZED
            );
        }
        // equipment host 주소 가져오기
        String host;
        Long equipmentId = videoStartReqDto.getEquipmentId();
        Optional<Equipment> byEquipmentIdx = equipmentRepository.findById(equipmentId);
        if (!byEquipmentIdx.isPresent())
            return new ResponseEntity(NoDataResponse.response(404, "NOT FOUND"), HttpStatus.NOT_FOUND);
        Equipment equipment = byEquipmentIdx.get();
        host = equipment.getEquipmentHost();
        String url = "http://"+host+"/record/{equipmentId}";

        // userId: body, equipmentId: param, [host]/record/{equipment_id} POST
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> urlParams = new HashMap<>();

        // path variable 추가
        urlParams.put("equipmentId", equipmentId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        // set header APPLICATION_JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // set JsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);

        // set request
        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

        return restTemplate.exchange(
                builder.buildAndExpand(urlParams).toUri(), HttpMethod.POST, request, String.class
        );
    }


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
