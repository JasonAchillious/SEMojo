package com.example.v1.semojo.entities.mongodb;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Document("texts")
public class TextMongo {
    private String _id;
    private Long textId;
    private Long productId;
    @Indexed
    private String name;
    private String contentType;
    private long size;
    private Date updateDate;
    @Indexed
    private String content;
    private String path;


    protected TextMongo() {
    }


    public TextMongo(Long productId, Long textId, String name,
                     String contentType, long size, Date updateDate, String content) {
        this.textId = textId;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.updateDate = updateDate;
        this.content = content;
        this.productId = productId;
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
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textId, name, contentType, size, updateDate, content, path);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
