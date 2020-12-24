package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ProductDetailModel;
import com.example.v1.semojo.api.model.TextSearchModel;
import com.example.v1.semojo.api.model.UserAllInfoModel;
import com.example.v1.semojo.api.model.UserInfoModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.mongoDAO.ProductMongoDao;
import com.example.v1.semojo.dao.mongoDAO.TextMongoDao;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import com.example.v1.semojo.entities.mongodb.ProductMongo;
import com.example.v1.semojo.entities.mongodb.TextMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductMongoDao productMongoDao;
    @Autowired
    TextMongoDao textMongoDao;
    @Resource
    private MongoTemplate mongoTemplate;

    public List<UserAllInfoModel> searchUserNameLike(String userName){
        List<UserAuth> userAuthList = userAuthDao.findByUsernameLike("%" + userName + "%");
        List<UserAllInfoModel> userInfoList = new ArrayList<>();
        if (userAuthList != null){
            for (UserAuth userAuth : userAuthList){
                User userTemp = userAuth.getUser();
                UserAllInfoModel n_model = new UserAllInfoModel();
                n_model.setUsername(userAuth.getUsername());
                n_model.setAddress(userTemp.getAddress());
                n_model.setAuths(userAuth);
                n_model.setEmail(userTemp.getEmail());
                n_model.setGender(userTemp.getGender());
                n_model.setPhoneNum(userTemp.getPhoneNum());
                n_model.setPortrait(userTemp.getPortrait());
                n_model.setQqNum(userTemp.getQqNum());
                n_model.setWeChatNum(userTemp.getWeChatNum());
                userInfoList.add(n_model);
            }
        }
        return userInfoList;
    }

    public Map<String, List> searchProduct(String keyword){
        List<Product> nameLikeProducts = productDao.findProductsByProductNameContaining(keyword);
        List<Product> descriptionLikeProducts = productDao.findProductsByOutlineContaining(keyword);
        List<ProductDetailModel> nameInfos = new ArrayList<>();
        List<ProductDetailModel> descriptionInfos = new ArrayList<>();
        for (Product product : nameLikeProducts){
            nameInfos.add(new ProductDetailModel(product));
        }
        for (Product product : descriptionLikeProducts){
            descriptionInfos.add(new ProductDetailModel(product));
        }

        Map<String, List> result = new HashMap<>();
        result.put("name", nameInfos);
        result.put("description", descriptionInfos);
        return result;
    }

    public Map<String,List> searchCode(String keyword){
        Query query = Query.query(Criteria.where("content").regex(keyword));
        List<TextMongo> textList = mongoTemplate.find(query, TextMongo.class);
        //List<TextMongo> textList = textMongoDao.findTextMongosByContentLike("%" + keyword + "%");
        Map<String,List> productCodes = new HashMap<>();
        for (TextMongo text: textList){
            long productId = text.getProductId();
            ProductMongo productMongo = productMongoDao.findProductMongoByProductId(productId);
            String productName = productMongo.getProductName();
            TextSearchModel textSearchModel = new TextSearchModel();
            textSearchModel.setContent(text.getContent());
            textSearchModel.setContentType(text.getContentType());
            textSearchModel.setDescription(productMongo.getDescription());
            textSearchModel.setName(text.getName());
            textSearchModel.setProductId(text.getProductId());
            textSearchModel.setSize(text.getSize());
            textSearchModel.setUpdateDate(text.getUpdateDate());
            textSearchModel.setProductName(productName);
            textSearchModel.setTextId(text.getTextId());
            List<TextSearchModel> codes;
            if (productCodes.containsKey(productName)) {
                codes = productCodes.get(productName);
                codes.add(textSearchModel);
            }else {
                codes = new ArrayList<>();
                codes.add(textSearchModel);
                productCodes.put(productName, codes);
            }
        }
        return productCodes;
    }
}
