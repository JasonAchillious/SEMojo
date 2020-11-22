package com.example.v1.semojo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/register")
    public String getSignUpPage(){
        return "signup.html";
    }
}
