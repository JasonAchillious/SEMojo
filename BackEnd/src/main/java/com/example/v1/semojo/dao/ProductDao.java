package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {
    Product findProductById(long productId);
}
