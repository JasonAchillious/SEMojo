package com.example.v1.semojo.api.model;


public class UserAuthModel {
    private Integer userId;
    private String username;
    private String password;

    public UserAuthModel(Integer userId, String username){
        this.username = username;
        this.userId = userId;
    }


}
