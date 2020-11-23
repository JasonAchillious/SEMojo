package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.api.util.ProductRespResultUtil;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.services.ProductService;
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

    @GetMapping("/products")
    public WebRespResult getProductList(@RequestParam int limit,
                                        @RequestParam int start,
                                        @RequestParam String tag,
                                        @RequestParam String lang){
        List<ProductPreviewModel> productList = productService.getProductList(limit, start, tag, lang);
        return ProductRespResultUtil.success(productList);
    }

    @GetMapping("/product/{productId}")
    public WebRespResult getProductInfo(@PathVariable Long productId){
        ProductDetailModel product = productService.findProductByProductId(productId);
        return ProductRespResultUtil.success(product);
    }

    @PostMapping("/contributor/product")
    public WebRespResult createProduct(String productName
    // TODO: 2020/11/22 add params
    ){
        return null;
    }

    @PutMapping("/product/{productId}")
    public WebRespResult updateProductInfo(){
        return null;
    }

    @DeleteMapping("contributor/product/{productId}")
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
            } catch (SignatureException | MalformedJwtException e) {
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
