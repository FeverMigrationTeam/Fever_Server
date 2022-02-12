package com.example.fever_server_test.components;


public class UserId<T> {
    private String idType;
    private String idName;
    private T value;

    public UserId(String idType, T value) {
        this.idType = idType;
        this.value = value;
        switch (idType) {
            case "KAKAO": this.idName = "user_kakao_id"; break;
            case "NAVER": this.idName = "user_naver_id"; break;
            case "GOOGLE": this.idName = "user_google_id"; break;
            default: this.idName = "user_idx"; break;
        }
    }

    public String getColName() {
        return this.idName;
    }

    public String getValue() {
        return this.value.toString();
    }
}
