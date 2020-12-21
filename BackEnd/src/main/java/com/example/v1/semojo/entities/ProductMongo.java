package com.example.v1.semojo.entities;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.List;

@Document(collection="products")
@CompoundIndexes({
        @CompoundIndex(name = "productName_description_idx", def = "{'productName': 1, 'description': -1}")
})
public class ProductMongo {
    @MongoId
    private String productId;
    @Indexed(unique = true)
    private String productName;
    @Indexed
    private String description;
    @DBRef
    private List<TextMongo> files;
    @DBRef
    private List<ArtifactMongo> artifacts;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TextMongo> getFiles() {
        return files;
    }

    public void setFiles(List<TextMongo> files) {
        this.files = files;
    }

    public List<ArtifactMongo> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactMongo> artifacts) {
        this.artifacts = artifacts;
    }
}
