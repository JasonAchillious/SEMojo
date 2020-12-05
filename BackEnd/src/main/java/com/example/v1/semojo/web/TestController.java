package com.example.v1.semojo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    RedisTemplate stringRedisTemplate;

    @PostMapping("/hello")
    public String hello(@RequestParam String key, @RequestParam String value) {
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        ops1.set(key, value);
        return  ops1.get(key);
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
