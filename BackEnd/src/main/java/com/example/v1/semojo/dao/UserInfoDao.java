package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    UserInfo findUserInfoByEmail(String email);
}
