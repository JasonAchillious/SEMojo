package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<ProductPreviewModel> getProductList(){
        List<ProductPreviewModel> result = new ArrayList<>();
        List<Product> products = productDao.findAll();
        for (Product productsTemp : products){
            ProductPreviewModel n_product = new ProductPreviewModel();
            n_product.setCreator(productsTemp.getCreator());
            n_product.setCurrentPrice(productsTemp.getCurrentPrice());
            n_product.setOutline(productsTemp.getOutline());
            n_product.setProductName(productsTemp.getProductName());
            n_product.setReviewStar(productsTemp.getReviewStar());
            n_product.setStatus(productsTemp.getStatus());
            n_product.setUpdate_time(productsTemp.getUpdate_time());
            result.add(n_product);
        }
        return result;
    }

    public Product findProductByProductId(){}

    public void createNewProduct(){

    }
}
