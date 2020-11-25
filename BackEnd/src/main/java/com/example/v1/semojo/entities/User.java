package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "semojo_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    private String gender;
    private String address;
    private String phoneNum;
    private String qqNum;
    private String weChatNum;
    private String portrait;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserAuth auth;

    @OneToMany(mappedBy = "booker", cascade = CascadeType.ALL)
    private List<Transaction> userTransec;

    @ManyToMany
    private List<Product> ownedProducts;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "questioner", cascade = CascadeType.ALL)
    private List<Issue> issues;

    public UserAuth getAuth() {
        return auth;
    }

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getWeChatNum() {
        return weChatNum;
    }

    public void setWeChatNum(String weChatNum) {
        this.weChatNum = weChatNum;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public List<Transaction> getUserTransec() {
        return userTransec;
    }

    public void setUserTransec(List<Transaction> userTransec) {
        this.userTransec = userTransec;
    }


}
