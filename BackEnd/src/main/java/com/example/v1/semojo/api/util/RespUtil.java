package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;

public class RespUtil {
    public static WebRespResult error(Integer code, String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static WebRespResult success(Integer code, String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
