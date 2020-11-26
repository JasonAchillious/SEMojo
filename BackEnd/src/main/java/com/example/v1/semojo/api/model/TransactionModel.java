package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.Transaction;
import com.example.v1.semojo.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Transaction.TransactionStatus status;
    private String booker;
    private List<String> products;

    public TransactionModel(Transaction transaction) {
        this.id = transaction.getId();
        this.createTime = transaction.getCreateTime();
        this.updateTime = transaction.getUpdateTime();
        this.status = transaction.getStatus();
        this.booker = transaction.getBooker().getAuth().getUsername();
        List<String> products = new ArrayList<>();
        for (Product product: transaction.getProducts()){
            products.add(product.getProductId() + product.getProductName());
        }
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Transaction.TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(Transaction.TransactionStatus status) {
        this.status = status;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
