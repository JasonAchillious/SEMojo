package com.example.v1.semojo.entities;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.util.Objects;

@Document(collation = "artifacts")
public class ArtifactMongo {
    @MongoId  // 主键
    private String textId;
    private String name;
    private String type;
    private long size;
    private Date updateDate;
    private String md5;
    private Binary content;
    private String path;

    protected ArtifactMongo() {
    }


    public ArtifactMongo(String name, String type, long size,  Binary content) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtifactMongo)) return false;
        ArtifactMongo fileModel = (ArtifactMongo) o;
        return size == fileModel.size &&
                Objects.equals(textId, fileModel.textId) &&
                Objects.equals(name, fileModel.name) &&
                Objects.equals(type, fileModel.type) &&
                Objects.equals(updateDate, fileModel.updateDate) &&
                Objects.equals(md5, fileModel.md5) &&
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textId, name, type, size, updateDate, md5, content, path);
    }
}
