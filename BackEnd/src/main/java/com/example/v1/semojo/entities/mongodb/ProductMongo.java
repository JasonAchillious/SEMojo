package com.example.v1.semojo.entities.mongodb;

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
    private String _id;
    private Long productId;
    @Indexed(unique = true)
    private String productName;
    @Indexed
    private String description;
    @DBRef
    private List<TextMongo> textFiles;
    @DBRef
    private List<ArtifactMongo> artifacts;

    public ProductMongo(Long productId, String productName, String description){
        this.productId = productId;
        this.productName = productName;
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public List<TextMongo> getTextFiles() {
        return textFiles;
    }

    public void setTextFiles(List<TextMongo> files) {
        this.textFiles = files;
    }

    public List<ArtifactMongo> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactMongo> artifacts) {
        this.artifacts = artifacts;
    }
}
