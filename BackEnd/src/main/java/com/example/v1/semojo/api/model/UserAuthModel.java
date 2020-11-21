package com.example.v1.semojo.api.model;


import java.util.List;

public class UserAuthModel {
    private Integer userId;
    private String username;
    private String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }

    private List<String> auths;

    public UserAuthModel(Integer userId, String username){
        this.username = username;
        this.userId = userId;
    }


}
