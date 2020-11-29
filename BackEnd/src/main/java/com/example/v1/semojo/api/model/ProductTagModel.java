package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.ProductTag;

import java.util.ArrayList;
import java.util.List;

public class ProductTagModel {
    private Long id;
    private List<Long> productId;
    private String tag;
    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ProductTagModel(ProductTag productTag) {
        this.id = productTag.getId();
        List<Long> productIdList = new ArrayList<>();
        for (Product product: productTag.getProducts()){
            productIdList.add(product.getProductId());
        }
        this.productId = productIdList;
        this.tag = productTag.getTag();
        this.detail = productTag.getDetail();
    }
}
