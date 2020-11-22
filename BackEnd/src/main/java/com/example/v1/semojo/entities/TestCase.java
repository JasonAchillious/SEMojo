package com.example.v1.semojo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "test_case")
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uploader;
    private String description;
    private Timestamp uploadTime;

    private String input;
    private String output;

    private String inputDescription;
    private String outPutDescription;

    private String location;

    private static enum testStatus{
        pass,
        fail,
        untested
    }
    private testStatus status ;

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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInputDescription() {
        return inputDescription;
    }

    public void setInputDescription(String inputDescription) {
        this.inputDescription = inputDescription;
    }

    public String getOutPutDescription() {
        return outPutDescription;
    }

    public void setOutPutDescription(String outPutDescription) {
        this.outPutDescription = outPutDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public testStatus getStatus() {
        return status;
    }

    public void setStatus(testStatus status) {
        this.status = status;
    }
}
