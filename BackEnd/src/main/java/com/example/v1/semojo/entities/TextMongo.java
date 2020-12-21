package com.example.v1.semojo.entities;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.util.Objects;

@Document(collation = "texts")
public class TextMongo {
    @MongoId  // 主键
    private String textId;
    private String name;
    private String contentType;
    private long size;
    private Date updateDate;
    private String md5;
    private Binary content;
    private String path;

    protected TextMongo() {
    }


    public TextMongo(String name, String contentType, long size,  Binary content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextMongo)) return false;
        TextMongo fileModel = (TextMongo) o;
        return size == fileModel.size &&
                Objects.equals(textId, fileModel.textId) &&
                Objects.equals(name, fileModel.name) &&
                Objects.equals(contentType, fileModel.contentType) &&
                Objects.equals(updateDate, fileModel.updateDate) &&
                Objects.equals(md5, fileModel.md5) &&
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textId, name, contentType, size, updateDate, md5, content, path);
    }
}
