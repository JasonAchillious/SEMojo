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


    @GetMapping("/register")
    public String getSignUpPage(){
        return "signup.html";
    }

    @GetMapping("/product")
    public String getProductPage(){
        return "product.html";
    }
    @GetMapping("/")
    public String getMainPage(){
        return "homepage.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping("/{username}/userpage")
    public String getUserPage(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        UserAuth auth = user.getAuth();
        switch (auth.getRole()){
            case 1: return "redirect:/customer/"+ username +"/userpage";
            case 2: return "redirect:/contributor/" + username +"/userpage";
            case 3: return "redirect:/admin/"+ username + "/userpage";
            default: return "redirect: /";
        }
    }

    @GetMapping("/customer/{username}/userpage")
    public String getCustomerPage(@PathVariable String username){
        System.out.println(username);
        return "forward:/customer.html";
    }

    @GetMapping("/contributor/{username}/userpage")
    public String getContributorPage(@PathVariable String username){
        return "contributor.html";
    }

    @GetMapping("/admin/{username}/userpage")
    public String getAdminPage(@PathVariable String username){
        return "administrator.html";
    }
}
