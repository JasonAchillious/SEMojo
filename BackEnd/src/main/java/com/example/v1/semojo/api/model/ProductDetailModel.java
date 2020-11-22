package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.*;

import java.sql.Timestamp;
import java.util.List;

public class ProductDetailModel {
    private String productName;
    private String outline;
    private int reviewStar;
    private int salesVolume;
    private Timestamp create_time;
    private Timestamp update_time;
    private String creator;
    private double fixPrice;
    private double currentPrice;
    private Product.ProductStatus status;

    private List<User> owners;
    private List<Artifact> artifacts;
    private List<Document> docs;
    private List<TestCase> testCases;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public double getFixPrice() {
        return fixPrice;
    }

    public void setFixPrice(double fixPrice) {
        this.fixPrice = fixPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Product.ProductStatus getStatus() {
        return status;
    }

    public void setStatus(Product.ProductStatus status) {
        this.status = status;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "ProductDetailModel{" +
                "productName='" + productName + '\'' +
                ", outline='" + outline + '\'' +
                ", reviewStar=" + reviewStar +
                ", salesVolume=" + salesVolume +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", creator='" + creator + '\'' +
                ", fixPrice=" + fixPrice +
                ", currentPrice=" + currentPrice +
                ", status=" + status +
                ", owners=" + owners +
                ", artifacts=" + artifacts +
                ", docs=" + docs +
                ", testCases=" + testCases +
                '}';
    }
}
