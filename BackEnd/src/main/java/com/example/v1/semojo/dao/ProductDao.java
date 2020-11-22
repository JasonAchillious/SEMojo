package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    Product findProductById(long productId);
    @Query(nativeQuery=true, value = "select *from product order by id limit ?1 offset ?2")
    List<Product> findProductsByIdLimit(long limit, long start);
}
