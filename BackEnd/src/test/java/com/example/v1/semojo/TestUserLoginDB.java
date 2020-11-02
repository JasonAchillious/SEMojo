package com.example.v1.semojo;

import com.example.v1.semojo.dao.UserDao;
import com.example.v1.semojo.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestUserLoginDB {

    @Autowired
    UserDao userDao;
    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User u1 = new User();
        u1.setUsername("ZhuFang");
        u1.setPassword(encoder.encode("11711623"));
        u1.setAccountNonExpired(true);
        u1.setAccountNonLocked(true);
        u1.setCredentialsNonExpired(true);
        u1.setEnabled(true);
        u1.setRole(1); // Customer
        userDao.save(u1);

        User u2 = new User();
        u2.setUsername("XuPingShen");
        u2.setPassword(encoder.encode("123"));
        u2.setAccountNonExpired(true);
        u2.setAccountNonLocked(true);
        u2.setCredentialsNonExpired(true);
        u2.setEnabled(true);
        u2.setRole(2); // Contributor
        userDao.save(u2);

        User u3 = new User();
        u3.setUsername("ChengQianFan");
        u3.setPassword(encoder.encode("123"));
        u3.setAccountNonExpired(true);
        u3.setAccountNonLocked(true);
        u3.setCredentialsNonExpired(true);
        u3.setEnabled(true);
        u3.setRole(3); // Admin
        userDao.save(u3);
    }
}
