package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.ProductResultEnum;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.api.util.ProductRespResultUtil;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.services.ProductService;
import com.example.v1.semojo.services.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductTagController {
    @Autowired
    ProductTagService productTagService;
    @Autowired
    ProductService productService;

    @PostMapping("/admin/{username}/tag")
    public WebRespResult createProductTag(@PathVariable String username,
                                          @RequestParam String tag,
                                          @RequestParam String detail
    ){
        if (productTagService.findProductTagByTag(tag) != null){
            return ProductRespResultUtil.error(ProductResultEnum.TAG_IS_EXIST.getCode(), ProductResultEnum.TAG_IS_EXIST.getMsg());
        }
        else {
            productTagService.saveNewProductTag(tag, detail);
            return new WebRespResult<>(200, "success", tag);
        }
    }

    // TODO: 2020/11/29 below hasn't been tested
    @PutMapping("/productTags")
    public WebRespResult getProductTagList(@RequestParam long limit,
                                            @RequestParam long start){

        return new WebRespResult<>(200, "success", productTagService.getProductTagList(limit, start));
    }

    @GetMapping("/productTag/{productTagId}")
    public WebRespResult getProductTagInfo(@PathVariable Long productTagId){
        return new WebRespResult<>(200, "success", productTagService.getProductTag(productTagId));
    }

    @PutMapping("/productTag/{productTagId}")
    public WebRespResult updateProductInfo( @PathVariable Long productTagId,
                                            @RequestParam String tag,
                                            @RequestParam String detail){
        if (productTagService.findProductTagByTagID(productTagId) == null){
            return ProductRespResultUtil.error(ProductResultEnum.TAG_NOT_EXIST.getCode(), ProductResultEnum.TAG_NOT_EXIST.getMsg());
        }
        return new WebRespResult<>(200, "success", productTagService.updateProductTag(productTagId, tag, detail));
    }
    @DeleteMapping("/admin/productTag/{productTagId}")
    public WebRespResult deleteProduct(@PathVariable Long productTagId){
        return null;
    }

}
