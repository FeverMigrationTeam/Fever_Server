package com.example.fever_server_test.response;

import org.springframework.stereotype.Component;

@Component
public class Status {

    /*
     * 200 success
     * 3XX input error
     * 4XX server error
     * 5XX database error
     */

    public int SUCCESS = 200;

    public int DUPLICATED_EMAIL = 301;
    public int NOT_ENTERED = 302;
    public int DUPLICATED_NICKNAME = 303;
    public int INVALID_ID = 304;
    public int EXISTED_NICKNAME = 305;
    public int DB_INVALID_VALUE = 500;
    public int DB_NO_DATA = 501;
    public int EXPIRED_TOKEN = 502;

}
