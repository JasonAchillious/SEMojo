package com.example.v1.semojo.entities;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;

    public static enum TransactionStatus {
        NotSubmit,
        WaitingProcess,
        Processing,
        PurchasedSuccess
    }
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @ManyToOne
    private User booker;

    @ManyToMany(mappedBy = "productTransac")
    private List<Product> products;

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public User getBooker() {
        return booker;
    }

    public void setBooker(User booker) {
        this.booker = booker;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
