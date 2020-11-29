package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTagDao extends JpaRepository<ProductTag, Long> {
    ProductTag findProductTagByTag(String tag);
    @Query(nativeQuery=true, value = "select *from product_tag order by id desc limit ?1 offset ?2")
    List<ProductTag> findProductTagsByLimitAndStart(long limit, long start);
    ProductTag findProductTagById(long id);
}
