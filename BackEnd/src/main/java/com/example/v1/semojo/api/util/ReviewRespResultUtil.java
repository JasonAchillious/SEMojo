package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.ReviewResultEnum;
import com.example.v1.semojo.api.model.ReviewModel;

public class ReviewRespResultUtil {
    public static WebRespResult<ReviewModel> success(ReviewModel reviewModel){
        WebRespResult<ReviewModel> result = new WebRespResult<>();
        result.setCode(ReviewResultEnum.SUCCESS.getCode());
        result.setMsg(ReviewResultEnum.SUCCESS.getMsg());
        result.setData(reviewModel);
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
