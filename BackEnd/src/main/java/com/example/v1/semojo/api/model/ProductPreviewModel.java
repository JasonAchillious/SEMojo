package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.*;
import java.sql.Timestamp;

public class ProductPreviewModel {
    private String productName;
    private String outline;
    private int reviewStar;
//    private int salesVolume;
//    private Timestamp create_time;
    private Timestamp update_time;
    private String creator;
//    private double fixPrice;
    private double currentPrice;
    private Product.ProductStatus status;

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

    @Override
    public String toString() {
        return "ProductPreviewModel{" +
                "productName='" + productName + '\'' +
                ", outline='" + outline + '\'' +
                ", reviewStar=" + reviewStar +
                ", update_time=" + update_time +
                ", creator='" + creator + '\'' +
                ", currentPrice=" + currentPrice +
                ", status=" + status +
                '}';
    }
}
