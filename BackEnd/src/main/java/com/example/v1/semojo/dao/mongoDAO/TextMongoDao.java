package com.example.v1.semojo.dao.mongoDAO;

import com.example.v1.semojo.entities.mongodb.TextMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TextMongoDao extends MongoRepository<TextMongo,String> {
    TextMongo findTextMongoByTextId(Long textId);
    List<TextMongo> findTextMongosByContentContaining(String keyword);


}
