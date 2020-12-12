package com.example.v1.semojo.web;

import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import com.example.v1.semojo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getMainPage(){
        return "index.html";
    }


}
