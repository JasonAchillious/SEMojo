package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public WebRespResult getProductList(){
        return null;
    }
}
