package com.example.v1.semojo.dao.mongoDAO;

import com.example.v1.semojo.entities.mongodb.ArtifactMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtifactMongoDao extends MongoRepository<ArtifactMongo,String> {
    ArtifactMongo findArtifactMongoByArtifactId(long Id);
}
