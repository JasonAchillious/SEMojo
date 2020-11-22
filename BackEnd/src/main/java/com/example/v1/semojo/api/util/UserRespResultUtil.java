package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.UserAuthModel;
import com.example.v1.semojo.api.model.UserInfoModel;

public class UserRespResultUtil {
    public static WebRespResult<UserAuthModel> success(UserAuthModel userAuthModel){
        WebRespResult<UserAuthModel> result = new WebRespResult<>();
        result.setCode(UserResultEnum.SUCCESS.getCode());
        result.setMsg(UserResultEnum.SUCCESS.getMsg());
        result.setData(userAuthModel);
        return result;
    }

    public static WebRespResult<UserInfoModel> success(UserInfoModel userInfoModel){
        WebRespResult<UserInfoModel> result = new WebRespResult<>();
        result.setCode(UserResultEnum.SUCCESS.getCode());
        result.setMsg(UserResultEnum.SUCCESS.getMsg());
        result.setData(userInfoModel);
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
