package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.*;

import java.util.List;

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

    public static WebRespResult<List<UserAllInfoModel>> success(List<UserAllInfoModel> userAllInfoModel){
        WebRespResult<List<UserAllInfoModel>> result = new WebRespResult<>();
        result.setCode(UserResultEnum.SUCCESS.getCode());
        result.setMsg(UserResultEnum.SUCCESS.getMsg());
        result.setData(userAllInfoModel);
        return result;
    }

    public static WebRespResult success(Integer code,String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
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
