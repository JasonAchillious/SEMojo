package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.UserAuthDao;
import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public User saveUserWithAuth(User user, UserAuth auth){
        user.setAuth(auth);
        auth.setUser(user);
        return userDao.save(user);
    }

    public User findUserByUsername(String username){
        UserAuth auth = userAuthDao.findUserAuthByUsername(username);
        return auth.getUser();
    }

    public void saveUser(User user, UserAuth auth){
        user.setAuth(auth);
        userDao.save(user);
        userAuthDao.save(auth);
    }

    public User saveUser(String username, String password, String confirmPassword, String email) throws Exception {
        if (!password.equals(confirmPassword)){
            throw new Exception("password matching is not the same");
        }
        User user = new User();
        user.setEmail(email);
        UserAuth auth = new UserAuth();
        auth.setUser(user);
        auth.setUsername(username);
        auth.setPassword(password);
        auth.setAccountNonExpired(true);
        auth.setAccountNonLocked(true);
        auth.setEnabled(true);
        auth.setCredentialsNonExpired(true);
        auth.setRole(1);
        user.setAuth(auth);
        return userDao.save(user);
    }
}
