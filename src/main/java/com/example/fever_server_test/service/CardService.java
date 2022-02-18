package com.example.fever_server_test.service;

import com.example.fever_server_test.dto.response.CardRepDto;
import com.example.fever_server_test.model.Entity.Card;
import com.example.fever_server_test.model.Entity.Member;
import com.example.fever_server_test.repository.CardRepository;
import com.example.fever_server_test.repository.MemberRepository;
import com.example.fever_server_test.response.DataResponse;
import com.example.fever_server_test.response.NoDataResponse;
import com.example.fever_server_test.response.ResponseMessage;
import com.example.fever_server_test.response.Status;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final 선언된 변수들의 생성자 만들어줌
public class CardService {

    private final Status status;
    private final ResponseMessage message;
    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity selectAllCards(Long userId) {
        Optional<Member> byId = memberRepository.findById(userId);

        if (!byId.isPresent()) // 유효성 검사
            return new ResponseEntity(NoDataResponse.response(status.INVALID_ID, message.INVALID_ID), HttpStatus.NOT_FOUND);
        Member member = byId.get();

        List<CardRepDto> allByMember = cardRepository.findAllByMember(member)
                .stream()
                .map(card -> modelMapper.map(card, CardRepDto.class))
                .collect(Collectors.toList());

        if (allByMember.isEmpty())
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA + " 등록된 카드가 없습니다. "), HttpStatus.NOT_FOUND);

        return new ResponseEntity(DataResponse.response(
                status.SUCCESS, "카드 조회하기 " + message.SUCCESS, allByMember
        ), HttpStatus.OK);

    }


}
