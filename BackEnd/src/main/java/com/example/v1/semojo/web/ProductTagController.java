package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.ProductResultEnum;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.api.util.ProductRespResultUtil;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.services.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductTagController {
    @Autowired
    ProductTagService productTagService;

    @PostMapping("/admin")
    public WebRespResult createProductTag(@RequestParam Long id,
                                          @RequestParam Long productId,
                                          @RequestParam String tag,
                                          @RequestParam String detail
    ){
//        if (productTagService.findProductTagByTag(tag) != null){
//            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_IS_EXISTS.getCode(), ProductResultEnum.PRODUCT_IS_EXISTS.getMsg());
//        }else if(userService.findUserByUsername(creator)==null){
//            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
//        }
//        else {
//            productService.saveNewProduct(productName, outline, authority, creator, fixed_price);
//            Product n_product = productService.findProductByProductName(productName);
//            ProductPreviewModel n_productModel = new ProductPreviewModel(n_product);
//            return ProductRespResultUtil.success(n_productModel);
//        }
        return null;
    }
}
