package com.example.v1.semojo.entities;

import javax.persistence.*;

@Entity(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    public static enum AuthType{
        update,
        download,
        delete,
        all
    }
    @Enumerated(EnumType.STRING)
    private AuthType name;
    private String description;

    public AuthType getName() {
        return name;
    }

    public void setName(AuthType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
