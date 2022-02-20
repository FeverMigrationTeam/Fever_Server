package com.example.fever_server_test.service;

import com.example.fever_server_test.dto.response.MyAlarmRespDto;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.AlarmRepository;
import com.example.fever_server_test.repository.MemberRepository;
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
public class AlarmService {

    private final Status status;
    private final ResponseMessage message;
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity selectAllAlarm(Long userId) {
        Optional<Member> byId = memberRepository.findById(userId);

        if (!byId.isPresent())
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID), HttpStatus.NOT_FOUND);

        Member member = byId.get();
        List<MyAlarmRespDto> allByMember = alarmRepository.findAllByMember(member)
                .stream()
                .map(alarm -> modelMapper.map(alarm, MyAlarmRespDto.class))
                .collect(Collectors.toList());

        if (allByMember.isEmpty())
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA + " 알림이 없습니다. "), HttpStatus.NOT_FOUND);

        return new ResponseEntity(DataResponse.response(
                status.SUCCESS, "알림 조회하기 " + message.SUCCESS, allByMember
        ), HttpStatus.OK);


    }
}
