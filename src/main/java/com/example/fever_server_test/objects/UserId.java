package com.example.fever_server_test.objects;


public class UserId<T> {
    private String idType;
    private String idName;
    private T value;

    public UserId(String idType, T value) {
        this.idType = idType;
        this.value = value;
        if (idType.equals("NORMAL")) {
            this.idName = "user_idx";
        } else { this.idName = "user_social_idx"; }
    }

    public String getColName() {
        return this.idName;
    }

    public String getValue() {
        return this.value.toString();
    }
}
