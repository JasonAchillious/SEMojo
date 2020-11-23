package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.FileResultEnum;
import com.example.v1.semojo.api.model.FileRespModel;
import com.example.v1.semojo.entities.AdditionalFile;

import java.util.List;

public class FileRespResultUtil {
    public static WebRespResult<FileRespModel> success(FileRespModel fileRespModel){
        WebRespResult<FileRespModel> result = new WebRespResult<>();
        result.setCode(FileResultEnum.SUCCESS.getCode());
        result.setMsg(FileResultEnum.SUCCESS.getMsg());
        result.setData(fileRespModel);
        return result;
    }
    public static WebRespResult error(Integer code,String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
