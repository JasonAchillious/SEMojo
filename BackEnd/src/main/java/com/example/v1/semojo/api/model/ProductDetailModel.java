package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.*;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    private List<String> tags;
    private List<String> owners;
    private List<String> artifacts;
    private List<String> docs;
    private List<String> testCases;

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

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public List<String> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<String> artifacts) {
        this.artifacts = artifacts;
    }

    public List<String> getDocs() {
        return docs;
    }

    public void setDocs(List<String> docs) {
        this.docs = docs;
    }

    public List<String> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<String> testCases) {
        this.testCases = testCases;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ProductDetailModel() {

    }

    public ProductDetailModel(Product product) {
        this.productName = product.getProductName();
        this.outline = product.getOutline();
        this.reviewStar = product.getReviewStar();
        this.salesVolume = product.getSalesVolume();
        this.create_time = product.getCreate_time();
        this.update_time = product.getUpdate_time();
        this.creator = product.getCreator();
        this.fixPrice = product.getFixPrice();
        this.currentPrice = product.getCurrentPrice();
        this.status = product.getStatus();
        List<String> owners = new ArrayList<>();
        List<String> artifacts = new ArrayList<>();
        List<String> docs = new ArrayList<>();
        List<String> testCases = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        for (User user:product.getOwners()){
            owners.add(user.getAuth().getUsername());
        }
        for (Artifact artifact: product.getArtifacts()){
            artifacts.add(artifact.getFileName());
        }
        for (Document document: product.getDocs()){
            docs.add(document.getFileName());
        }
        for (TestCase testCase: product.getTestCases()){
            testCases.add(testCase.getFileName());
        }
        for (ProductTag tag: product.getTags()){
            tags.add(tag.getTag());
        }
        this.owners = owners;
        this.artifacts = artifacts;
        this.docs = docs;
        this.testCases = testCases;
        this.tags = tags;
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
