package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoDao extends MongoRepository<ProductMongo,String> {

}
