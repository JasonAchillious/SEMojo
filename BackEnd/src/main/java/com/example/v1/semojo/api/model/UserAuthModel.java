package com.example.v1.semojo.api.model;


import java.util.List;


public class UserAuthModel {
    private Long userId;
    private String username;
    private String auths;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuths() {
        return auths;
    }

    public void setAuths(String auths) {
        this.auths = auths;
    }

    public UserAuthModel(Long userId, String username, String auth){
        this.username = username;
        this.userId = userId;
        this.auths = auth;
    }


}
