package com.example.fever_server_test.response;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
    /*
     * Set same variable name class Status's variable name
     */

    public String SUCCESS = "성공";
    public String SUCCESS_SELECT_ALL_STADIUM = "모든 구장 검색 성공";
    public String SUCCESS_SEARCH_STADIUM = "구장 검색 성공";

    public String NOT_ENTERED = "입력되지 않은 필수 항목이 있습니다.";
    public String DUPLICATED_NICKNAME = "사용할 수 없는 닉네임입니다.(중복)";
    public String NOT_VALID_ACCOUNT = "계정 정보를 불러올 수 없습니다."; // accountId validation
    public String DB_NO_DATA = "DB에 데이터가 없습니다.";
    public String EXPIRED_TOKEN = "REFRESH TOKEN이 만료되었습니다. 새롭게 로그인 해주세요.";
    public String EXISTED_NICKNAME = "이미 닉네임이 존재합니다. 다른 닉네임을 작성해 주세요.";

}
