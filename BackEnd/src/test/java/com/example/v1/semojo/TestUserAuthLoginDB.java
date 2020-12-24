//package com.example.v1.semojo;
//
//import com.example.v1.semojo.dao.UserAuthDao;
//import com.example.v1.semojo.entities.UserAuth;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@SpringBootTest
//public class TestUserAuthLoginDB {
//
//    @Autowired
//    UserAuthDao userAuthDao;
//
//
//    @Test
//    void contextLoads() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        UserAuth u1 = new UserAuth();
//        u1.setUsername("ZhuFang");
//        u1.setPassword(encoder.encode("11711623"));
//        u1.setAccountNonExpired(true);
//        u1.setAccountNonLocked(true);
//        u1.setCredentialsNonExpired(true);
//        u1.setEnabled(true);
//        u1.setRole(1); // Customer
//        userAuthDao.save(u1);
//
//        UserAuth u2 = new UserAuth();
//        u2.setUsername("XuPinShen");
//        u2.setPassword(encoder.encode("123"));
//        u2.setAccountNonExpired(true);
//        u2.setAccountNonLocked(true);
//        u2.setCredentialsNonExpired(true);
//        u2.setEnabled(true);
//        u2.setRole(2); // Contributor
//        userAuthDao.save(u2);
//
//        UserAuth u3 = new UserAuth();
//        u3.setUsername("ChenQianFan");
//        u3.setPassword(encoder.encode("123"));
//        u3.setAccountNonExpired(true);
//        u3.setAccountNonLocked(true);
//        u3.setCredentialsNonExpired(true);
//        u3.setEnabled(true);
//        u3.setRole(3); // Admin
//        userAuthDao.save(u3);
//    }
//}
