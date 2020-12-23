package com.example.v1.semojo.dao.mongoDAO;

import com.example.v1.semojo.entities.mongodb.TextMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextMongoDao extends MongoRepository<TextMongo,String> {
    TextMongo findTextMongoByTextId(long textId);
}
