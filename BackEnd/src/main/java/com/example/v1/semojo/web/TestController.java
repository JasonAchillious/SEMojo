package com.example.v1.semojo.web;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class TestController {

    @PostMapping("/hello")
    public String hello(String uploadDir, HttpServletRequest req) {
        try {
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/uploadFile/" + uploadDir;
            File folder = new File(realPath, uploadDir);
            if (!folder.isDirectory()){
                folder.mkdirs();
                return realPath;
            }
            return realPath + "-error";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  "error";
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
