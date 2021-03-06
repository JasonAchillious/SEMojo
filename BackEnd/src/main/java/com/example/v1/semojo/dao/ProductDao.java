package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    Product findProductByProductId(long productId);

    @Query(nativeQuery=true, value = "select *from product order by sales_volume desc limit ?1 offset ?2")
    List<Product> findProductsByLimitAndStart(long limit, long start);
    Product findProductByProductName(String productName);
    List<Product> findProductsByProductNameContaining(String productName);
    List<Product> findProductsByOutlineContaining(String keyword);
}
