package com.example.v1.semojo.api.enums;

public enum FileResultEnum {
    UNKNOWN_ERROR(30500,"unknown error"),
    SUCCESS(30200,"success"),
    WRONG_TYPE(30401,"Wrong type"),
    FILE_IS_NOT_EXIST(30402, "File is not Exist")
    ;

    private Integer code;
    private String msg;

    FileResultEnum(Integer code, String msg) {
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
