package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    UserAuthDao userAuthDao;

    public List<ProductPreviewModel> getProductList( int limit, int start, String tag, String lang){
        List<ProductPreviewModel> result = new ArrayList<>();
        List<Product> products;
        if (start==-1||limit==-1){
            products = productDao.findAll();
        }else {
            products = productDao.findProductsByLimitAndStart(limit, start);
        }
        for (Product productsTemp : products){
            ProductPreviewModel n_product = new ProductPreviewModel();
            n_product.setProductId(productsTemp.getProductId());
            n_product.setCreator(productsTemp.getCreator());
            n_product.setCurrentPrice(productsTemp.getCurrentPrice());
            n_product.setOutline(productsTemp.getOutline());
            n_product.setProductName(productsTemp.getProductName());
            n_product.setReviewStar(productsTemp.getReviewStar());
            n_product.setStatus(productsTemp.getStatus());
            n_product.setUpdate_time(productsTemp.getUpdate_time());
            Map<String, Integer> languageMap = new HashMap<>();
            Map<String, Integer> tagMap = new HashMap<>();
            List<Artifact> languages = productsTemp.getArtifacts();
            List<ProductTag> tags = productsTemp.getTags();
            int maxLang = 0;
            int maxTag = 0;
            for (int i = 0; i < languages.size(); i++){
                String Lang_temp = languages.get(i).getLang().toString();
                if (languageMap.containsKey(Lang_temp)){
                    languageMap.put(Lang_temp, languageMap.get(Lang_temp) + 1);
                    if(languageMap.get(Lang_temp) > maxLang){
                        maxLang = languageMap.get(Lang_temp);
                    }
                }else {
                    languageMap.put(Lang_temp, 1);
                }
            }
            for (int i = 0; i < tags.size(); i++){
                String Tag_temp = tags.get(i).getTag().toString();
                if (tagMap.containsKey(Tag_temp)){
                    tagMap.put(Tag_temp, tagMap.get(Tag_temp) + 1);
                    if (tagMap.get(Tag_temp) > maxTag){
                        maxTag = tagMap.get(Tag_temp);
                    }
                }else {
                    tagMap.put(Tag_temp, 1);
                }
            }
            Iterator<Map.Entry<String, Integer>> lang_it = languageMap.entrySet().iterator();
            Iterator<Map.Entry<String, Integer>> tag_it = tagMap.entrySet().iterator();
            while(lang_it.hasNext()){
                Map.Entry<String, Integer> entry = lang_it.next();
                if(entry.getValue().equals(maxLang)){
                    n_product.setLanguage(entry.getKey());
                    break;
                }
            }
            while(tag_it.hasNext()){
                Map.Entry<String, Integer> entry = tag_it.next();
                if(entry.getValue().equals(maxTag)){
                    n_product.setTags(entry.getKey());
                    break;
                }
            }
            result.add(n_product);
        }
        if (result.size()>400){
            return result.subList(0, 400);
        }else {
            return result;
        }
    }

    public ProductDetailModel findProductByProductId(long productId){
        Product product = productDao.findProductByProductId(productId);
        ProductDetailModel result = new ProductDetailModel();
        result.setArtifacts(product.getArtifacts());
        result.setCreate_time(product.getCreate_time());
        result.setCreator(product.getCreator());
        result.setCurrentPrice(product.getCurrentPrice());
        result.setDocs(product.getDocs());
        result.setFixPrice(product.getFixPrice());
        result.setOutline(product.getOutline());
        result.setOwners(product.getOwners());
        result.setProductName(product.getProductName());
        result.setReviewStar(product.getReviewStar());
        result.setSalesVolume(product.getSalesVolume());
        result.setStatus(product.getStatus());
        result.setTestCases(product.getTestCases());
        result.setUpdate_time(product.getUpdate_time());
        return result;
    }

    public void saveNewProduct(String productName, String outline, String authority, String creator, double fixed_price){
        Product n_product = new Product();
        n_product.setProductName(productName);
        n_product.setOutline(outline);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        n_product.setCreate_time(d);
        n_product.setUpdate_time(d);
        Authority n_authority = new Authority();
        n_authority.setProductId(n_product.getProductId());
        n_product.setSalesVolume(0);
        n_product.setCreator(creator);
        n_product.setFixPrice(fixed_price);
        List<User> owners = new ArrayList<>();
        UserAuth t_userAuth = userAuthDao.findUserAuthByUsername(creator);

//        t_userAuth.setAuthorities();
        User t_creator = t_userAuth.getUser();
        owners.add(t_creator);
        n_product.setOwners(owners);
        n_product.setStatus(Product.ProductStatus.developing);
        productDao.save(n_product);

    }

    public Product findProductByProductName(String productName){
        return productDao.findProductByProductName(productName);
    }

    public void updateProduct(){

    }

    public void deleteProductByProductId(long productId){
        productDao.deleteById(productId);
    }
}
