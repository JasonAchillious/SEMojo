package com.example.v1.semojo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return"hello";
    }

    @GetMapping("/customer/hello")
    public String custormer() {
        return "custormer";
    }

    @GetMapping("/contributor/hello")
    public String contributor() {
        return "contributor";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }
}
