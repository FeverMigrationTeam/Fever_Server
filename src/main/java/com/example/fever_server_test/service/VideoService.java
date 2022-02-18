package com.example.fever_server_test.service;


import com.example.fever_server_test.model.Entity.Equipment;
import com.example.fever_server_test.objects.UserId;
import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.repository.EquipmentRepository;
import com.example.fever_server_test.repository.VideoRepository;

import com.example.fever_server_test.dto.response.ProjectionVideoAllRepDto;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.repository.VideoRepository;
import com.example.fever_server_test.response.DataResponse;

import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
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
        try {
            UserId userId = memberService.verifyUser(loginType, token);
        } catch (Exception e) {
            return new ResponseEntity(
                    NoDataResponse.response(401, "UNAUTHORIZED"), HttpStatus.UNAUTHORIZED
            );
        }

        // equipment host 주소 가져오기
        String host;
        Equipment equipment = equipmentRepository.findByEquipmentIdx(videoStartReqDto.getEquipmentId())
                .orElseThrow(Exception::new);
        host = equipment.getEquipmentHost();

        // userId.getValue(): body, equipmentId: param, [host]/record/{equipment_id}

        return new ResponseEntity(
                NoDataResponse.response(200, "SUCCESS"), HttpStatus.OK);
    }

    public ResponseEntity selectMyVideo ( Long userId){
        Optional<Member> byId = memberRepository.findById(userId);

        if(!byId.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID), HttpStatus.NOT_FOUND);

        List<ProjectionVideoAllRepDto> allByMemberOrderByVideoIdxQuery = videoRepository.findAllByMemberOrderByVideoIdxQuery(byId.get().getUserIdx())
                .stream()
                .map(video -> modelMapper.map(video,ProjectionVideoAllRepDto.class))
                .collect(Collectors.toList());

        if (allByMemberOrderByVideoIdxQuery.isEmpty())
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA + " 비디오가 없습니다. "), HttpStatus.OK);


        return new ResponseEntity(DataResponse.response(status.SUCCESS, " 나의 비디오 조회 " + message.SUCCESS, allByMemberOrderByVideoIdxQuery), HttpStatus.OK);

    }
}
