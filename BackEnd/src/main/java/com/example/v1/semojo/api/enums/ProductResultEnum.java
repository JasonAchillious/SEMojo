package com.example.v1.semojo.api.enums;

import com.example.v1.semojo.entities.Product;

public enum ProductResultEnum {
    UNKNOWN_ERROR(20500,"unknown error"),
    SUCCESS(20200,"success"),
    PRODUCT_NOT_EXIST(20401,"product not exist"),
    PRODUCT_IS_EXIST(20402,"product has existed"),
    NO_AUTHORITY(20403, "No authority"),
    TAG_IS_EXIST(20404, "tag has existed")
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
