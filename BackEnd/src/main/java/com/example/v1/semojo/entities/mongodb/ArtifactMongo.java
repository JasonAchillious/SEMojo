package com.example.v1.semojo.entities.mongodb;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Document("artifacts")
public class ArtifactMongo {
    private String _id;
    private Long artifactId;
    private Long productId;
    @Indexed
    private String name;
    private String type;
    private Long size;
    private Date updateDate;
    @Indexed
    private String md5;
    private Binary content;
    private String path;

    protected ArtifactMongo() {
    }


    public ArtifactMongo(Long productId, Long artifactId, String name, String type, long size,
                         Binary content, Date updateDate, String path) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.content = content;
        this.artifactId = artifactId;
        this.updateDate = updateDate;
        this.path = path;
        this.productId = productId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtifactMongo)) return false;
        ArtifactMongo fileModel = (ArtifactMongo) o;
        return size == fileModel.size &&
                Objects.equals(artifactId, fileModel.artifactId) &&
                Objects.equals(name, fileModel.name) &&
                Objects.equals(type, fileModel.type) &&
                Objects.equals(updateDate, fileModel.updateDate) &&
                Objects.equals(md5, fileModel.md5) &&
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactId, name, type, size, updateDate, md5, content, path);
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

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
