package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByUserId(long userId);
    @Query(nativeQuery=true, value = "select *from semojo_user order by userId limit ?1 offset ?2")
    List<User> findUsersByIdLimit(long limit, long start);
    User findUserByEmail(String email);
}
