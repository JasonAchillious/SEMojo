package com.example.v1.semojo.domain;

public class User {
    private long id;
    private String name;
    private long authId;
    private long infoId;

    public User(long id, String name, long authId, long infoId){
        this.id = id;
        this.name = name;
        this.authId = authId;
        this.infoId = infoId;
    }

    public User(long id, String name, long authId){
        this.id = id;
        this.name = name;
        this.authId = authId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthId() {
        return authId;
    }

    public void setAuthId(long authId) {
        this.authId = authId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }
}
