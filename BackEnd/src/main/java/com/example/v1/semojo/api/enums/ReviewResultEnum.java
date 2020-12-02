package com.example.v1.semojo.api.enums;

import com.example.v1.semojo.entities.Review;

public enum  ReviewResultEnum {
    UNKNOWN_ERROR(50500,"unknown error"),
    SUCCESS(50200,"success"),
    REVIEW_NOT_EXIST(50401,"issue not exist"),
    REVIEW_IS_EXIST(50402,"product has existed"),
    NO_AUTHORITY(50403, "No authority"),
    SUBREVIEW_IS_EXIST(50404, "tag has existed"),
    SUBREVIEW_NOT_EXIST(50405, "tag not existed");

    private Integer code;
    private String msg;

    ReviewResultEnum(Integer code, String msg) {
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
