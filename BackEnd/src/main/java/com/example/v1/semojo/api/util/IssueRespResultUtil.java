package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.IssueResultEnum;
import com.example.v1.semojo.api.model.IssueModel;
import com.example.v1.semojo.entities.Issue;

import java.util.List;

public class IssueRespResultUtil {
    public static WebRespResult<IssueModel> success(IssueModel issueModel){
        WebRespResult<IssueModel> result = new WebRespResult<>();
        result.setCode(IssueResultEnum.SUCCESS.getCode());
        result.setMsg(IssueResultEnum.SUCCESS.getMsg());
        result.setData(issueModel);
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
