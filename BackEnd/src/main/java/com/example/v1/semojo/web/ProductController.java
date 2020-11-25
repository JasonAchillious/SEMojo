package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.ProductResultEnum;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.api.util.ProductRespResultUtil;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Authority;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.services.ProductService;
import com.example.v1.semojo.services.ProductTagService;
import com.example.v1.semojo.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductTagService productTagService;
    @Autowired
    UserService userService;

    @GetMapping("/products")
    public WebRespResult getProductList(@RequestParam int limit,
                                        @RequestParam int start,
                                        @RequestParam String tag,
                                        @RequestParam String lang){
        System.out.println(limit);
        System.out.println(start);
        System.out.println(tag);
        System.out.println(lang);
        List<ProductPreviewModel> productList = productService.getProductList(limit, start, tag, lang);
        return ProductRespResultUtil.success(productList);
    }

    @GetMapping("/product/{productId}")
    public WebRespResult getProductInfo(@PathVariable Long productId){
        ProductDetailModel product = productService.findProductDetailByProductId(productId);
        return ProductRespResultUtil.success(product);
    }

    @PostMapping("/contributor/{username}/product")
    public WebRespResult createProduct(@PathVariable String username,
                                       @RequestParam String productName,
                                       @RequestParam String outline,
                                       @RequestParam String authority,
                                       @RequestParam double fixed_price
    ){
        if (productService.findProductByProductName(productName) != null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_IS_EXIST.getCode(), ProductResultEnum.PRODUCT_IS_EXIST.getMsg());
        }else if(userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }
        else {
            productService.saveNewProduct(productName, outline, authority, username, fixed_price);
            Product n_product = productService.findProductByProductName(productName);
            ProductPreviewModel n_productModel = new ProductPreviewModel(n_product);
            return ProductRespResultUtil.success(n_productModel);
        }
    }

    @PutMapping("/product/{productId}")
    public WebRespResult updateProductInfo(@PathVariable long productId,
                                           @RequestParam String productName,
                                           @RequestParam String outline,
                                           @RequestParam double currentPrice,
                                           @RequestParam String status,
                                           @RequestParam("contributors[]") List<String> contributors,
                                           @RequestParam("tags[]") List<String> tags){
        if (productService.findProductByProductId(productId) == null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else{
            productService.updateProduct(productId, productName, outline, currentPrice, status, contributors, tags);
        }
        return null;
    }

    @DeleteMapping("/contributor/product/{productId}")
    public WebRespResult deleteProduct(@PathVariable Long productId,
                                       HttpServletRequest req){
        String jwtToken = req.getHeader("authorization");
        if (jwtToken != null && jwtToken.trim().length() > 0) {
            System.out.println("jwtToken: " + jwtToken);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey("zzx@11711621")
                        .parseClaimsJws(jwtToken.replace("Bearer", ""))
                        .getBody();
                String username = claims.getSubject();
                List<GrantedAuthority> authorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                boolean hasAuth = false;
                for (GrantedAuthority auth: authorities){
                    if (auth.getAuthority().equals(productId + "-" + Authority.AuthType.delete.toString())
                    || auth.getAuthority().equals(productId + "-" + Authority.AuthType.all.toString())){
                        hasAuth = true;
                    }
                }
                if (hasAuth){
                    if (productService.findProductByProductId(productId) == null){
                        return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
                    }else {
                        productService.deleteProductByProductId(productId);
                        return ProductRespResultUtil.success(ProductResultEnum.SUCCESS.getCode(), ProductResultEnum.SUCCESS.getMsg());
                    }
                }
                else {
                    return ProductRespResultUtil.error(ProductResultEnum.NO_AUTHORITY.getCode(), ProductResultEnum.NO_AUTHORITY.getMsg());
                }
            } catch (SignatureException | MalformedJwtException e) {
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
