package com.example.v1.semojo.api.model;


import com.example.v1.semojo.entities.Authority;

import java.util.List;


public class UserAuthModel {
    private long userId;
    private String username;
    private int role;
    private List<Authority> auths;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Authority> getAuths() {
        return auths;
    }

    public void setAuths(List<Authority> auths) {
        this.auths = auths;
    }

    public UserAuthModel(long userId, String username, int role, List<Authority> auths){
        this.username = username;
        this.userId = userId;
        this.role = role;
        this.auths = auths;
    }


}
