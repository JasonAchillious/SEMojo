package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductTagModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.ProductTagDao;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTagService {
    @Autowired
    ProductTagDao productTagDao;
    @Autowired
    ProductDao productDao;

    public ProductTag findProductTagByTag(String tag) {
        return productTagDao.findProductTagByTag(tag);
    }

    public void saveNewProductTag(String tag, String detail){
        ProductTag productTag = new ProductTag();
        productTag.setTag(tag);
        productTag.setDetail(detail);
        productTagDao.save(productTag);
    }

    // TODO: 2020/11/29 below hasn't been tested
    public ProductTag findProductTagByTagID(long productId){
        return productTagDao.findProductTagById(productId);
    }

    public List<ProductTagModel> getProductTagList(long limit, long start){
        List<ProductTag> productTags = new ArrayList<>();
        if (limit == -1||start == -1){
            productTags = productTagDao.findAll();
        }else {
            productTags = productTagDao.findProductTagsByLimitAndStart(limit, start);
        }
        List<ProductTagModel> productTagModels = new ArrayList<>();
        for (ProductTag productTag: productTags){
            productTagModels.add(new ProductTagModel(productTag));
        }
        return productTagModels;
    }

    public ProductTagModel getProductTag(long productTagId){
        return new ProductTagModel(productTagDao.findProductTagById(productTagId));
    }

    public ProductTagModel updateProductTag(Long productTagId, String tag, String detail){
        ProductTag productTag = productTagDao.findProductTagById(productTagId);
        productTag.setTag(tag);
        productTag.setDetail(detail);
        return new ProductTagModel(productTagDao.save(productTag));
    }

    public void deleteProductTag(long productTagId){
        productTagDao.deleteById(productTagId);
    }
}
