package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.ProductPreviewModel;
import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductTagDao productTagDao;
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityDao authorityDao;

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
            List<Artifact> languages = productsTemp.getArtifacts();
            List<ProductTag> tags = productsTemp.getTags();
            List<String> tagList = new ArrayList<>();
            int maxLang = 0;
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
                tagList.add(tags.get(i).getTag());
            }
            n_product.setTags(tagList);
            Iterator<Map.Entry<String, Integer>> lang_it = languageMap.entrySet().iterator();
            while(lang_it.hasNext()){
                Map.Entry<String, Integer> entry = lang_it.next();
                if(entry.getValue().equals(maxLang)){
                    n_product.setLanguage(entry.getKey());
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
    public Product findProductByProductId(long productId){
        return productDao.findProductByProductId(productId);
    }

    public ProductDetailModel findProductDetailByProductId(long productId){
        Product product = productDao.findProductByProductId(productId);
        ProductDetailModel result = new ProductDetailModel(product);
        return result;
    }

    public void saveNewProduct(String productName, String outline,String authority, String creator, double fixed_price){
        Product n_product = new Product();
        n_product.setProductName(productName);
        n_product.setOutline(outline);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        n_product.setCreate_time(d);
        n_product.setUpdate_time(d);
        n_product.setSalesVolume(0);
        n_product.setCreator(creator);
        n_product.setFixPrice(fixed_price);
        productDao.save(n_product);
        List<User> owners = new ArrayList<>();
        UserAuth t_userAuth = userAuthDao.findUserAuthByUsername(creator);
        Authority n_auth = new Authority();
        n_auth.setProductId(n_product.getProductId());
        n_auth.setName(Authority.AuthType.all);
        authorityDao.save(n_auth);
        List<Authority> n_authorities;
        if (t_userAuth.getAuthority()!=null){
            n_authorities = t_userAuth.getAuthority();
        }else {
            n_authorities = new ArrayList<>();
        }
        n_authorities.add(n_auth);
        t_userAuth.setAuthorities(n_authorities);
        User t_creator = t_userAuth.getUser();
        List<Product> products;
        if (t_creator.getOwnedProducts()!=null){
            products = t_creator.getOwnedProducts();
        }else {
            products = new ArrayList<>();
        }
        products.add(n_product);
        t_creator.setOwnedProducts(products);
        userAuthDao.save(t_userAuth);
        userDao.save(t_creator);
        owners.add(t_creator);
        n_product.setOwners(owners);
        n_product.setStatus(Product.ProductStatus.developing);
        productDao.save(n_product);
    }

    public Product findProductByProductName(String productName){
        return productDao.findProductByProductName(productName);
    }

    public void updateProduct(long productId, String productName, String outline, double currentPrice, String status, String contributors, String tags){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        Product t_product = findProductByProductId(productId);
        t_product.setProductName(productName);
        t_product.setOutline(outline);
        t_product.setCurrentPrice(currentPrice);
        t_product.setStatus(Product.ProductStatus.valueOf(status));
        t_product.setUpdate_time(d);
        productDao.save(t_product);
        String[] contributorsArray = contributors.split("_");
        String[] tagsArray = tags.split("_");
        for (String contributor : contributorsArray) {
            System.out.println(contributor);
            UserAuth t_userAuth = userAuthDao.findUserAuthByUsername(contributor);
            Authority n_auth = new Authority();
            n_auth.setProductId(t_product.getProductId());
            n_auth.setName(Authority.AuthType.all);

            List<Authority> userAuths;
            if (t_userAuth.getAuthorities()!=null){
                userAuths = t_userAuth.getAuthority();
            }else {
                userAuths = new ArrayList<>();
            }
            if (!userAuths.contains(n_auth)){
                System.out.println(n_auth.getName());
                userAuths.add(n_auth);
                t_userAuth.setAuthorities(userAuths);
                List<User> owners = t_product.getOwners();
                owners.add(t_userAuth.getUser());
                t_product.setOwners(owners);
                User user = t_userAuth.getUser();
                List<Product> products ;
                if (user.getOwnedProducts()!=null){
                    products = user.getOwnedProducts();
                }else {
                    products = new ArrayList<>();
                }
                products.add(t_product);
                user.setOwnedProducts(products);
                productDao.save(t_product);
                userAuthDao.save(t_userAuth);
                userDao.save(user);
            }
        }
        for (String tag : tagsArray){
            List<ProductTag> t_tags;
            System.out.println(tag);
            if (t_product.getTags()!=null){
                t_tags = t_product.getTags();
            }else {
                t_tags = new ArrayList<>();
            }
            ProductTag t_tag = productTagDao.findProductTagByTag(tag);
            System.out.println(t_tag.getTag());
            if (!t_tags.contains(t_tag)){
                t_tags.add(t_tag);
                List<Product> products;
                if (t_tag.getProducts()!= null){
                    products = t_tag.getProducts();
                }else {
                    products = new ArrayList<>();
                }
                products.add(t_product);
                t_tag.setProducts(products);
                t_product.setTags(t_tags);
                productTagDao.save(t_tag);
                productDao.save(t_product);
            }
        }
        productDao.save(t_product);
    }

    public void deleteProductByProductId(long productId){
        Product t_product = findProductByProductId(productId);
        List<User> users = t_product.getOwners();
        for (User user: users){
            List<Authority> authorities = user.getAuth().getAuthority();
            for (Authority authority : authorities){
                authorities.remove(authority);
                authorityDao.delete(authority);
            }
        }
        productDao.deleteById(productId);
    }
}
