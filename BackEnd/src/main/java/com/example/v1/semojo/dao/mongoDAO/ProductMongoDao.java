package com.example.v1.semojo.dao.mongoDAO;

import com.example.v1.semojo.entities.mongodb.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductMongoDao extends MongoRepository<ProductMongo,String> {
    ProductMongo findProductMongoByProductId(Long productId);
    List<ProductMongo> findProductMongosByProductNameContaining(String productName);
    List<ProductMongo> findProductMongosByDescriptionContaining(String description);
}
