package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserById(long userId);
    List<User> findUsersByIdBetween(long start, long end);
    User findUserByEmail(String email);
}
