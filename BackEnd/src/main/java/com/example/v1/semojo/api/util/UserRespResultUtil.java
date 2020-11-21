package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.UserAuth;

public class UserRespResultUtil {
    public static WebRespResult<UserAuth> success(UserAuth userModel){
        WebRespResult<UserAuth> result = new WebRespResult<>();
        result.setCode(UserResultEnum.SUCCESS.getCode());
        result.setMsg(UserResultEnum.SUCCESS.getMsg());
        result.setData(userModel);
        return result;
    }

    /**失败**/
    public static WebRespResult error(Integer code,String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
