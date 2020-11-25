package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductPreviewModel {
    private long productId;
    private String productName;
    private String outline;
    private int reviewStar;
    private Timestamp update_time;
    private String creator;
    private String tags;
    private String language;
    private double currentPrice;
    private Product.ProductStatus status;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

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
                ", tags='" + tags + '\'' +
                ", language='" + language + '\'' +
                ", currentPrice=" + currentPrice +
                ", status=" + status +
                '}';
    }

    public ProductPreviewModel() {
    }
    public ProductPreviewModel(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.outline = product.getOutline();
        this.reviewStar = product.getReviewStar();
        this.update_time = product.getUpdate_time();
        this.creator = product.getCreator();
        if (product.getTags()!=null){
            this.tags = product.getTags().toString();
        }
        if (product.getArtifacts()!=null){
            List<Artifact> languages = product.getArtifacts();
            Map<String, Integer> languageMap = new HashMap<>();
            int maxLang = 0;
            for (int i = 0; i < languages.size(); i++){
                String Lang_temp = languages.get(i).getLang().toString();
                if (languageMap.containsKey(Lang_temp)){
                    languageMap.put(Lang_temp, languageMap.get(Lang_temp) + 1);
                    if(languageMap.get(Lang_temp) > maxLang){
                        maxLang = languageMap.get(Lang_temp);
                    }
                }else {
                    languageMap.put(Lang_temp, 1);
                }
            }
            Iterator<Map.Entry<String, Integer>> lang_it = languageMap.entrySet().iterator();
            while(lang_it.hasNext()){
                Map.Entry<String, Integer> entry = lang_it.next();
                if(entry.getValue().equals(maxLang)){
                    this.language = entry.getKey();
                    break;
                }
            }
        }
        this.currentPrice = product.getCurrentPrice();
        this.status = product.getStatus();
    }

    public ProductPreviewModel(int productId, String productName, String outline, int reviewStar, Timestamp update_time, String creator, String tags, String language, double currentPrice, Product.ProductStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.outline = outline;
        this.reviewStar = reviewStar;
        this.update_time = update_time;
        this.creator = creator;
        this.tags = tags;
        this.language = language;
        this.currentPrice = currentPrice;
        this.status = status;
    }
}
