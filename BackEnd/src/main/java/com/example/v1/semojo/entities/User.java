package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "semojo_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    private String gender;
    private String address;
    private String phoneNum;
    private String qqNum;
    private String weChatNum;
    private String portrait;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_id")
    private UserAuth auth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private List<Product> products;

    @OneToMany(mappedBy = "booker", cascade = CascadeType.ALL)
    private List<Transaction> userTransec;


    public UserAuth getAuth() {
        return auth;
    }

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Transaction> getUserTransec() {
        return userTransec;
    }

    public void setUserTransec(List<Transaction> userTransec) {
        this.userTransec = userTransec;
    }


}
