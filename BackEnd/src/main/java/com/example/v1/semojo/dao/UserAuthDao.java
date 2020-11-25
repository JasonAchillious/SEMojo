package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthDao extends JpaRepository<UserAuth, Long> {
    UserAuth findUserAuthByUsername(String username);
    List<UserAuth> findUserAuthByRoleBetween(int start, int end);
    List<UserAuth> findUserAuthByRoleEquals(int role);
}
