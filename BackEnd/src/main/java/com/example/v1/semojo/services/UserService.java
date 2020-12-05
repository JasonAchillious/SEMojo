package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.UserAllInfoModel;
import com.example.v1.semojo.api.model.UserInfoModel;
import com.example.v1.semojo.dao.ProductDao;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.Authority;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EntityManager entityManager;
    @Autowired
    ProductDao productDao;

    String defaultPortait = "/images/touxiang.png";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthDao.findUserAuthByUsername(username);
        if (userAuth == null) {
            throw new UsernameNotFoundException("User not exists");
        }

        return userAuth;
    }

    public void saveUserWithAuth(User user, long authId){
        UserAuth info = entityManager.getReference(UserAuth.class, authId);
        info.setUser(user);
        userDao.save(user);
    }

    public void saveUserWithAuth(User user, UserAuth auth){
        user.setAuth(auth);
        auth.setUser(user);
        userDao.save(user);
        userAuthDao.save(auth);
    }

    public User findUserByUsername(String username){
        UserAuth auth = userAuthDao.findUserAuthByUsername(username);
        if (auth == null) {
            return null;
        }else return auth.getUser();
    }

    public User findUserByEmail(String email){
        return userDao.findUserByEmail(email);
    }
    public void saveUser(String username, String password, String email){
        User n_user = new User();
        n_user.setEmail(email);
        UserAuth n_auth = new UserAuth();
        n_auth.setUser(n_user);
        n_auth.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(password);
        n_auth.setPassword(encodedPwd);
        n_auth.setAccountNonExpired(true);
        n_auth.setAccountNonLocked(true);
        n_auth.setEnabled(true);
        n_auth.setCredentialsNonExpired(true);
        n_auth.setRole(1);
        n_user.setAuth(n_auth);
        n_user.setPortrait(defaultPortait);
        userDao.save(n_user);
        userAuthDao.save(n_auth);
    }

    public User findUserByUserId(long userId){
        return userDao.findUserByUserId(userId);
    }

    public void updateUser(String username, UserInfoModel userInfoModel){
        User n_user = findUserByUsername(username);
        n_user.setEmail(userInfoModel.getEmail());
        n_user.setWeChatNum(userInfoModel.getWeChatNum());
        n_user.setQqNum(userInfoModel.getQqNum());
        n_user.setPhoneNum(userInfoModel.getPhoneNum());
//        n_user.setPortrait(userInfoModel.getPortrait());
        n_user.setGender(userInfoModel.getGender());
        n_user.setAddress(userInfoModel.getAddress());
        userDao.save(n_user);
    }

    public void deleteUserByUserName(String username){
        userDao.deleteById(findUserByUsername(username).getUserId());
    }

    public List<UserAllInfoModel> getUserList(long limit, long start){
        List<UserAllInfoModel> result = new ArrayList<>();
        List<User> users = userDao.findUsersByIdLimit(start, limit);
        for (User userTemp : users) {
            UserAuth authTemp = userTemp.getAuth();
            UserAllInfoModel n_model = new UserAllInfoModel();
            n_model.setUsername(authTemp.getUsername());
            n_model.setAddress(userTemp.getAddress());
            n_model.setAuths(authTemp);
            n_model.setEmail(userTemp.getEmail());
            n_model.setGender(userTemp.getGender());
            n_model.setPhoneNum(userTemp.getPhoneNum());
            n_model.setPortrait(userTemp.getPortrait());
            n_model.setQqNum(userTemp.getQqNum());
            n_model.setWeChatNum(userTemp.getWeChatNum());
            result.add(n_model);
        }
        return result;
    }
    public void changePassword(){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPwd = encoder.encode(password);
//        boolean bool = encoder.matches("1234569077", encodedPwd);
    }

    public String getPortrait(String username) throws Exception {
        User user = findUserByUsername(username);
        if (user == null){
            throw new Exception("User is not exist");
        }
        return user.getPortrait();
    }

    public UserInfoModel getUserInfo(String username) throws Exception{
        User user = findUserByUsername(username);
        UserInfoModel info = new UserInfoModel();
        info.setAddress(user.getAddress());
        info.setEmail(user.getEmail());
        info.setGender(user.getGender());
        info.setPhoneNum(user.getPhoneNum());
        info.setWeChatNum(user.getWeChatNum());
        info.setPortrait(user.getPortrait());
        info.setQqNum(user.getQqNum());
        return info;
    }

    public List<Product> getPurchasedProduct(String username) throws Exception{
        User user = findUserByUsername(username);
        List<Product> products = user.getOwnedProducts();
        return products;
    }

    public List<UserAuth> getNeededAuthUsers() throws Exception{
        List<UserAuth> neededAuthUserAuths = userAuthDao.findUserAuthsByRoleEquals(1000);

        return neededAuthUserAuths;
    }

    public void updateUserRole(String username, int role) throws Exception{
        User user = findUserByUsername(username);
        user.getAuth().setRole(role);
        userDao.save(user);
    }

    public List<Product> getContributedProducts(String username){
        User user = findUserByUsername(username);
        ArrayList<Product> contributedProducts = new ArrayList<>();
        List<Authority> auths = user.getAuth().getAuthority();
        if (auths != null) {
            ArrayList<Long> productIds = new ArrayList<>();
            for (Authority au : auths) {
                productIds.add(au.getProductId());
            }
            for (Long id: productIds){
                contributedProducts.add(productDao.findProductByProductId(id));
            }
        }
        return contributedProducts;
    }
}
