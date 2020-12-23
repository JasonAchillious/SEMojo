package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.ArtifactMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtifactMongoDao extends MongoRepository<ArtifactMongo,String> {

}
