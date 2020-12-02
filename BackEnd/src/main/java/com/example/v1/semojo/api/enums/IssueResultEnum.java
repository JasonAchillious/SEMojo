package com.example.v1.semojo.api.enums;

public enum  IssueResultEnum {
    UNKNOWN_ERROR(40500,"unknown error"),
    SUCCESS(40200,"success"),
    ISSUE_NOT_EXIST(40401,"issue not exist"),
    ISSUE_IS_EXIST(40402,"product has existed"),
    NO_AUTHORITY(40403, "No authority"),
    SUBISSUE_IS_EXIST(40404, "tag has existed"),
    SUBISSUE_NOT_EXIST(40405, "tag not existed");

    private Integer code;
    private String msg;

    IssueResultEnum(Integer code, String msg) {
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
