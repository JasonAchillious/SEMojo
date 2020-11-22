package com.example.v1.semojo.api.enums;

public enum UserResultEnum {
    UNKNOWN_ERROR(10500,"unknown error"),
    SUCCESS(10200,"success"),
    USER_NOT_EXIST(10401,"user not exist"),
    USER_IS_EXISTS(10402,"user has existed"),
    DATA_IS_NULL(10403,"data is null"),
    CONFIRM_PASSWORD_MISMATCH(10404, "confirm password mismatch"),
    PASSWORD_MISMATCH(10405, "password mismatch")
    ;
    private Integer code;
    private String msg;

    UserResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
