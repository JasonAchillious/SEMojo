package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.UserInfoModel;
import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthDao.findUserAuthByUsername(username);
        if (userAuth == null) {
            throw new UsernameNotFoundException("用户不存在");
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
        userDao.save(n_user);
        userAuthDao.save(n_auth);
    }

    public User findUserByUserId(long userId){
        return userDao.findUserById(userId);
    }

    public void updateUser(String username, UserInfoModel userInfoModel){
        User n_user = findUserByUsername(username);
        n_user.setEmail(userInfoModel.getEmail());
        n_user.setWeChatNum(userInfoModel.getWeChatNum());
        n_user.setQqNum(userInfoModel.getQqNum());
        n_user.setPhoneNum(userInfoModel.getPhoneNum());
        n_user.setPortrait(userInfoModel.getPortrait());
        n_user.setGender(userInfoModel.getGender());
        n_user.setAddress(userInfoModel.getAddress());
        userDao.save(n_user);
    }

    public void changePassword(){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPwd = encoder.encode(password);
//        boolean bool = encoder.matches("1234569077", encodedPwd);
    }
}
