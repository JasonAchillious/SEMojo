package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "source_code")
public class SourceCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uploader;
    private String description;
    private Timestamp uploadTime;
    private String location;
    private Timestamp updateTime;
    private String fileName;

    public static enum Lang{
        cplusplus,
        java,
        python,
        c,
        golang,
        javascript
    }

    @Enumerated(EnumType.STRING)
    private Lang lang;

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
}
