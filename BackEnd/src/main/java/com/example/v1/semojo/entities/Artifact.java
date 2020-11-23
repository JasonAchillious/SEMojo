package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "artifact")
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uploader;
    private String description;

    private Timestamp upload_time;
    private Timestamp update_time;
    private String location;
    private String version;
    private String fileName;

    @Enumerated(EnumType.STRING)
    private SourceCode.Lang lang;

    public static enum ArtifactStatus{
        beta,
        finalVersion,
        deprecated;
    }
    @Enumerated(EnumType.STRING)
    private ArtifactStatus status;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public SourceCode.Lang getLang() {
        return lang;
    }

    public void setLang(SourceCode.Lang lang) {
        this.lang = lang;
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

    public Timestamp getUpload_time() {
        return upload_time;
    }

    public void setUploadTime(Timestamp upload_time) {
        this.upload_time = upload_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArtifactStatus getStatus() {
        return status;
    }

    public void setStatus(ArtifactStatus status) {
        this.status = status;
    }
}