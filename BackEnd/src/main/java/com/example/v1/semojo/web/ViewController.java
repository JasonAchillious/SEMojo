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
        int role = auth.getRole();
        if (role == 1 || 1000 <= role && role < 1010){
            return "redirect:/customer/"+ username +"/userpage";
        }else if (role == 2){
            return "redirect:/contributor/" + username +"/userpage";
        }else if (role == 3){
            return "redirect:/admin/"+ username + "/userpage";
        }else {
            return "wrong username";
        }
    }

    @GetMapping("/customer/{username}/userpage")
    public String getCustomerPage(@PathVariable String username){
        System.out.println(username);
        return "forward:/customer.html";
    }

    @GetMapping("/contributor/{username}/userpage")
    public String getContributorPage(@PathVariable String username){
        return "forward:/contributor.html";
    }

    @GetMapping("/admin/{username}/userpage")
    public String getAdminPage(@PathVariable String username){
        return "forward:/administrator.html";
    }

    @GetMapping("/contributor/{username}/product/{productId}/info_page")
    public String getProductInfoPage(@PathVariable String username,
                                 @PathVariable String productId){
        System.out.println(username + "---------------------------");
        return "forward:/ProductDetail.html";
    }

    @GetMapping("/contributor/{username}/product/{productId}/edit_page")
    public String getProductPage(@PathVariable String username,
                                 @PathVariable String productId){
        System.out.println(productId + "++++++++++++++++++++++++++");
        return "forward:/EditProduct.html";
    }

    @GetMapping("/product/{productId}/info_page)")
    public String getProductInfoPage(@PathVariable String productId){
        return "forward:/ProductDetail.html";
    }

}
