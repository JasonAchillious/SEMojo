package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long> {
    User findUsersByName(String name);

}
