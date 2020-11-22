package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public WebRespResult getProductList(){
        return null;
    }

    @GetMapping("/product/{productId}")
    public WebRespResult getProductInfo(@PathVariable Long productId){
        return null;
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

    @DeleteMapping("admin/product/{productId}")
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
