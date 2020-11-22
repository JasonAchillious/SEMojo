package com.example.v1.semojo.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String outline;
    private int reviewStar;
    private int salesVolume;
    private Timestamp create_time;
    private Timestamp update_time;
    private String creator;

    private static enum ProductStatus {
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
}
