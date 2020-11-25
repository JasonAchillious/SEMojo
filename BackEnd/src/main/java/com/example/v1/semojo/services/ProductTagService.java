package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductPreviewModel;
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

//    public ProductPreviewModel setNewProductTag(long productId, String tag){
//        Product t_product = productDao.findProductByProductId(productId);
//        List<ProductTag> tags;
//        if (t_product.getTags()!=null){
//            tags = t_product.getTags();
//        }else {
//            tags = new ArrayList<>();
//        }
//        ProductTag productTag = productTagDao.findProductTagByTag(tag);
//        tags.add(productTag);
//        t_product.setTags(tags);
//        productDao.save(t_product);
//
//    }
}
