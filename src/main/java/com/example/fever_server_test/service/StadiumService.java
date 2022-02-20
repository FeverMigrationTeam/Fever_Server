package com.example.fever_server_test.service;


import com.example.fever_server_test.dto.response.StadiumRespDto;
import com.example.fever_server_test.repository.StadiumRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final Status status;
    private final ResponseMessage message;
    public final StadiumRepository stadiumRepository;
    public final ModelMapper modelMapper;

    /* 모든 구장검색 : selectStadium */
    public ResponseEntity selectAllStadiums() {

        List<StadiumRespDto> stadiumRepDtoList = stadiumRepository.findAll()
                .stream()
                .map(stadium -> modelMapper.map(stadium, StadiumRespDto.class))
                .collect(Collectors.toList());

        if (stadiumRepDtoList.isEmpty()) // DB에 구장정보 x
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA), HttpStatus.OK);

        return new ResponseEntity(DataResponse.response(status.SUCCESS,
                message.SUCCESS_SELECT_ALL_STADIUM, stadiumRepDtoList), HttpStatus.OK);
    }

    /* 구장 검색 : searchStadium */
    public ResponseEntity searchStadium(String stadiumName) {

        List<StadiumRespDto> stadiumRepDtoList = stadiumRepository.findByStadiumNameContaining(stadiumName)
                .stream()
                .map(stadium -> modelMapper.map(stadium, StadiumRespDto.class))
                .collect(Collectors.toList());

        if (stadiumRepDtoList.isEmpty()) // DB에 구장정보 x
            return new ResponseEntity(NoDataResponse.response(status.DB_NO_DATA, message.DB_NO_DATA), HttpStatus.OK);

        return new ResponseEntity(DataResponse.response(status.SUCCESS,
                message.SUCCESS_SEARCH_STADIUM, stadiumRepDtoList), HttpStatus.OK);

    }
}
