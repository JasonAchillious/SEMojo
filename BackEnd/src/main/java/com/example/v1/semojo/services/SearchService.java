package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.UserAllInfoModel;
import com.example.v1.semojo.api.model.UserInfoModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    ProductDao productDao;

    public List<UserAllInfoModel> searchUserNameLike(String userName){
        List<UserAuth> userAuthList = userAuthDao.findByUsernameLike(userName);
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
}
