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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
