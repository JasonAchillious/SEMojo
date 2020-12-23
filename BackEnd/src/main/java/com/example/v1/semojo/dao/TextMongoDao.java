package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.TextMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextMongoDao extends MongoRepository<TextMongo,String> {
}
