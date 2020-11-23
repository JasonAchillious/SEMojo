package com.example.v1.semojo.api.util;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.ProductResultEnum;
import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.api.model.UserAllInfoModel;
import com.example.v1.semojo.entities.Product;

import java.util.List;

public class ProductRespResultUtil {
    public static WebRespResult<List<ProductPreviewModel>> success(List<ProductPreviewModel> productPreviewModels){
        WebRespResult<List<ProductPreviewModel>> result = new WebRespResult<>();
        result.setCode(ProductResultEnum.SUCCESS.getCode());
        result.setMsg(ProductResultEnum.SUCCESS.getMsg());
        result.setData(productPreviewModels);
        return result;
    }

    public static WebRespResult<ProductDetailModel> success(ProductDetailModel productDetailModel){
        WebRespResult<ProductDetailModel> result = new WebRespResult<>();
        result.setCode(ProductResultEnum.SUCCESS.getCode());
        result.setMsg(ProductResultEnum.SUCCESS.getMsg());
        result.setData(productDetailModel);
        return result;
    }

    public static WebRespResult<ProductPreviewModel> success(ProductPreviewModel productPreviewModel){
        WebRespResult<ProductPreviewModel> result = new WebRespResult<>();
        result.setCode(ProductResultEnum.SUCCESS.getCode());
        result.setMsg(ProductResultEnum.SUCCESS.getMsg());
        result.setData(productPreviewModel);
        return result;
    }

    public static WebRespResult success(Integer code,String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static WebRespResult error(Integer code,String msg){
        WebRespResult result = new WebRespResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
