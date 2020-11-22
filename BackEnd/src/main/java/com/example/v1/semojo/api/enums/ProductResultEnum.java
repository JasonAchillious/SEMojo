package com.example.v1.semojo.api.enums;

public enum ProductResultEnum {
    UNKNOWN_ERROR(10500,"unknown error"),
    SUCCESS(10200,"success")
    ;
    private Integer code;
    private String msg;

    ProductResultEnum(Integer code, String msg) {
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
