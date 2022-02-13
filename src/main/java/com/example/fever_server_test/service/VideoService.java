package com.example.fever_server_test.service;

import com.example.fever_server_test.model.Entity.Equipment;
import com.example.fever_server_test.objects.UserId;
import com.example.fever_server_test.dto.request.VideoStartReqDto;
import com.example.fever_server_test.repository.EquipmentRepository;
import com.example.fever_server_test.repository.VideoRepository;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final Status status;
    private final ResponseMessage message;
    private final VideoRepository videoRepository;
    private final EquipmentRepository equipmentRepository;

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
}
