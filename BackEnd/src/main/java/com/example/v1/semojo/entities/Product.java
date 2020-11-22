package com.example.v1.semojo.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String outline;
    private int reviewStar;
    private int salesVolume;
    private Timestamp create_time;
    private Timestamp update_time;
    private String creator;
    private double fixPrice;
    private double currentPrice;

    public static enum ProductStatus {
        developing,
        alpha,
        beta,
        finalVersion,
        deprecated;
    }
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToMany(mappedBy = "products")
    private List<User> owners;

    @OneToMany
    private List<Artifact> artifacts;

    @OneToMany
    private List<Document> docs;

    @OneToMany
    private List<TestCase> testCases;

    @ManyToMany
    private List<Transaction> productTransac;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
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

    public List<Transaction> getProductTransac() {
        return productTransac;
    }

    public void setProductTransac(List<Transaction> productTransac) {
        this.productTransac = productTransac;
    }
}
