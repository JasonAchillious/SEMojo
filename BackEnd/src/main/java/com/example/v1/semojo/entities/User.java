package com.example.v1.semojo.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    private UserAuth auth;

    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo info;

    public com.example.v1.semojo.domain.User convertToDomainUser(){
        return new com.example.v1.semojo.domain.User(
                this.Id,
                this.name,
                (this.auth == null) ? 0 : this.auth.getId(),
                (this.info == null) ? 0 : this.info.getId()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserAuth getAuth() {
        return auth;
    }

    public void setAuth(UserAuth auth) {
        auth.setUser(this);
        this.auth = auth;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        info.setUser(this);
        this.info = info;
    }



}
