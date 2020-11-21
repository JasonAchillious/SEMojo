package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.dao.UserInfoDao;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import com.example.v1.semojo.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    UserInfoDao userInfoDao;
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
        return auth.getUser();
    }

    public void saveUser(User user, UserAuth auth, UserInfo info){
        user.setAuth(auth);
        user.setInfo(info);
        userDao.save(user);
        userAuthDao.save(auth);
        userInfoDao.save(info);
    }

}
