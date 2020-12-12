package com.example.v1.semojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SemojoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SemojoApplication.class, args);
    }
}
