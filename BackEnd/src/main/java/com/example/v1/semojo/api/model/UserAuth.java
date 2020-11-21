package com.example.v1.semojo.api.model;


import java.io.Serializable;

public class UserAuth {
    private Integer userId;
    private String username;
    private String password;

    public UserAuth(Integer userId, String username){
        this.username = username;
        this.userId = userId;
    }


}
