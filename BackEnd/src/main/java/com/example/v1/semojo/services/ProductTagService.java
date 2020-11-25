package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.ProductTagDao;
import com.example.v1.semojo.entities.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTagService {
    @Autowired
    ProductTagDao productTagDao;

    public ProductTag findProductTagByTag(String tag) {
        return null;
    }
}
